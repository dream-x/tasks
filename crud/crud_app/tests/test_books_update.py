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

    def test_single_update(self):
        book = Book.objects.create(**self.data)

        changed_data = self.data.copy()
        changed_data['title'] = 'string3'

        response = self.client.put(
            self.url(book.pk),
            data=changed_data,
        )

        self.assertEquals(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.data['title'], changed_data['title'])

    def test_multiple_update(self):
        book = Book.objects.create(**self.data)

        changed_data = self.data.copy()

        for i in range(2):
            changed_data['title'] = f'string{i}'

            response = self.client.put(
                self.url(book.pk),
                data=changed_data,
            )

            self.assertEquals(response.status_code, status.HTTP_200_OK)
            self.assertEqual(response.data['title'], changed_data['title'])
