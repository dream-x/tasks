from rest_framework import serializers

from mainapp.models import Book


class BookSerializer(serializers.ModelSerializer):
    class Meta:
        model = Book
        fields = [
            'id',
            'title',
            'author',
            'age_rating',
            'is_available',
            'description',
        ]
