import random

from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase

from mainapp.models import Book


class TestBooksUpdate(APITestCase):
    @classmethod
    def setUpTestData(cls):
        cls.url = lambda x: reverse('api:books-detail', kwargs={'pk': x})
        cls.data = {
            'title': 'string',
            'author': 'string',
            'age_rating': 'M',
            'is_available': True,
            'description': 'string'
        }

    def test_single_delete(self):
        book = Book.objects.create(**self.data)

        response = self.client.delete(
            self.url(book.pk),
        )

        self.assertEquals(response.status_code, status.HTTP_204_NO_CONTENT)

    def test_delete_not_existing(self):
        response = self.client.delete(
            self.url(random.randint(100, 200)),
        )

        self.assertEquals(response.status_code, status.HTTP_404_NOT_FOUND)
