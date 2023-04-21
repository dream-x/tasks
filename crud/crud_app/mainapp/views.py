from django.http import FileResponse
from rest_framework import viewsets, status
from rest_framework.decorators import action
from rest_framework.response import Response

from mainapp.models import Book
from mainapp.serializers import BookCreateUpdateSerializer, BookGetSerializer
from utils.helpers import prepare_hash


class BooksViewSet(viewsets.ModelViewSet):
    """
    All CRUD operations for model Book
    """
    serializer_class = BookGetSerializer
    queryset = Book.objects.all()

    def get_serializer_class(self):
        serializer_class = self.serializer_class
        if self.request.method in ('PUT', 'POST'):
            serializer_class = BookCreateUpdateSerializer
        return serializer_class

    def create(self, request, *args, **kwargs):
        """Changing default create method to make it idempotent"""

        request_hash = prepare_hash(request.data)
        request.data.update({'req_hash': request_hash})

        obj = Book.objects.filter(req_hash=request_hash).first()

        if not obj:
            return super().create(request, *args, **kwargs)

        return Response(
            self.get_serializer(obj).data,
            status=status.HTTP_200_OK,
        )

    @action(
        detail=False,
        methods=['get'],
        url_path='actions/xls'
    )
    def get_xls(self, request):

        result = Book.create_xls()

        response = FileResponse(
            result,
            filename='Books.xls',
            content_type='application/vnd.ms-excel'
        )

        return response
