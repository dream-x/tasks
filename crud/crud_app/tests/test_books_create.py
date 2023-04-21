from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase


class TestBooksCreate(APITestCase):
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

    def test_single_create(self):
        response = self.client.post(
            self.url,
            data=self.data,
            format='json'
        )

        self.assertEquals(response.status_code, status.HTTP_201_CREATED)
        self.assertIn('id', response.data)
        response.data.pop('id')
        self.assertDictEqual(response.data, self.data)

    def test_multiple_create(self):
        data_2 = self.data.copy()
        data_2['title'] = 'string2'

        for data in (self.data, data_2):
            response = self.client.post(
                self.url,
                data=data,
                format='json'
            )

            self.assertEquals(response.status_code, status.HTTP_201_CREATED)
            self.assertIn('id', response.data)
            response.data.pop('id')
            self.assertDictEqual(response.data, data)

    def test_multiple_create_idempotent(self):
        response = self.client.post(
            self.url,
            data=self.data,
            format='json'
        )
        self.assertEquals(response.status_code, status.HTTP_201_CREATED)

        expected = self.data.copy()
        expected['id'] = response.data['id']

        for _ in range(2):
            response = self.client.post(
                self.url,
                data=self.data,
                format='json'
            )
            self.assertEquals(response.status_code, status.HTTP_200_OK)
            self.assertDictEqual(response.data, expected)
