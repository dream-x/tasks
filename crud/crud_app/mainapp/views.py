from rest_framework import viewsets
from .models import Book
from .serializers import BookSerializer


class BooksViewSet(viewsets.ModelViewSet):
    """
    All CRUD operations for model Book
    """
    serializer_class = BookSerializer
    queryset = Book.objects.all()
