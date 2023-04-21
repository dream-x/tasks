from rest_framework import serializers

from mainapp.models import Book


class BookCreateUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Book
        fields = [
            'id',
            'req_hash',
            'title',
            'author',
            'age_rating',
            'is_available',
            'description',
        ]

    def to_representation(self, instance):
        representation = super().to_representation(instance)
        representation.pop('req_hash', None)
        return representation


class BookGetSerializer(serializers.ModelSerializer):
    class Meta:
        model = Book
        exclude = [
            'req_hash'
        ]
