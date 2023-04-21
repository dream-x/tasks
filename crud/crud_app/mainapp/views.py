import hashlib
import json

from rest_framework import viewsets, status
from rest_framework.response import Response

from .models import Book
from .serializers import BookCreateUpdateSerializer, BookGetSerializer


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

        data = json.dumps(request.data).encode('utf-8')
        request_hash = hashlib.sha512(data).hexdigest()

        request.data.update({'req_hash': request_hash})
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)

        obj = Book.objects.filter(req_hash=request_hash).first()

        if not obj:
            return super().create(request, *args, **kwargs)

        return Response(
                self.get_serializer(obj).data,
                status=status.HTTP_200_OK,
            )
