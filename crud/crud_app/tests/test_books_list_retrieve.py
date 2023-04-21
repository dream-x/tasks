from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase

from mainapp.models import Book


class TestBooksRetrieve(APITestCase):
    @classmethod
    def setUpTestData(cls):
        cls.url = reverse('api:books-list')
        cls.data = {
            'title': 'string',
            'author': 'string',
            'age_rating': 'M',
            'is_available': True,
            'description': 'string'
        }

    def test_list(self):
        Book.objects.create(**self.data)
        Book.objects.create(**self.data)
        Book.objects.create(**self.data)

        response = self.client.get(self.url)
        self.assertEquals(response.status_code, status.HTTP_200_OK)
        self.assertEquals(len(response.data), 3)

    def test_retrieve(self):
        book = Book.objects.create(**self.data)

        detail_url = reverse('api:books-detail', kwargs={'pk': book.pk})
        response = self.client.get(detail_url)

        self.assertEquals(response.status_code, status.HTTP_200_OK)

        expected = self.data.copy()
        expected['id'] = book.pk
        response.data.pop('created', None)
        response.data.pop('modified', None)

        self.assertEquals(response.data, expected)
