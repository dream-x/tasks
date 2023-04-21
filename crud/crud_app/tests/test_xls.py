import io

import pandas as pd
from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase

from mainapp.models import Book


class TestBooksUpdate(APITestCase):
    @classmethod
    def setUpTestData(cls):
        cls.url = reverse('api:books-get-xls')
        cls.data = {
            'title': 'string',
            'author': 'string',
            'age_rating': 'M',
            'is_available': True,
            'description': 'string'
        }

    def test_xlx_file_empty(self):
        result = Book.create_xls()
        df = pd.read_excel(result)

        self.assertEquals(df.shape[0], 0)

    def test_xlx_file(self):
        Book.objects.create(**self.data)
        Book.objects.create(**self.data)

        result = Book.create_xls()
        df = pd.read_excel(result)

        self.assertEquals(df.shape[0], 2)

        cols_expected = [
            'Unnamed: 0',
            'id',
            'created',
            'modified',
            'title',
            'author',
            'age_rating',
            'is_available',
            'description'
        ]

        self.assertEquals(list(df.columns), cols_expected)

    def test_single_update(self):
        Book.objects.create(**self.data)
        Book.objects.create(**self.data)

        response = self.client.get(self.url)
        self.assertEquals(response.status_code, status.HTTP_200_OK)
        self.assertEquals(
            response.headers['Content-Type'],
            'application/vnd.ms-excel'
        )

        buffer = io.BytesIO()
        for chunk in response.streaming_content:
            buffer.write(chunk)

        df = pd.read_excel(buffer)

        self.assertEquals(df.shape[0], 2)
        self.assertEquals(df.shape[1], 9)
