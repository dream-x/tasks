from django.contrib.auth import get_user_model
from rest_framework.test import APITestCase


class TestAuthapp(APITestCase):

    def test_authapp_registration(self):
        response = self.client.post('http://127.0.0.1:9000/api/auth/v1/users/',
                                    data={
                                        "email": "ilya@mail.ru",
                                        "username": "admin",
                                        "password": "123456lid"
                                    })
        self.assertEqual(response.status_code, 201)
        self.assertEqual(response.data,
                         {'email': 'ilya@mail.ru', 'username': 'admin'})
        created_user = get_user_model().objects.get(pk=2)
        self.assertEqual('admin', created_user.username)

        response = self.client.post('http://127.0.0.1:9000/api/auth/v1/users/',
                                    data={
                                        "email": "ilya@mail.ru",
                                        "username": "admin",
                                        "password": "123456lid"
                                    })
        self.assertEqual(response.status_code, 400)

        response = self.client.post('http://127.0.0.1:9000/api/auth/v1/users/',
                                    data={"username": "admin2",
                                          "password": "123456lid"})
        self.assertEqual(response.status_code, 400)

    def test_authapp_create_jwt(self):
        response = self.client.post(
            'http://127.0.0.1:9000/api/auth/v1/jwt/create/',
            data={
                "username": "admin",
                "password": "123456lid"
            })
        self.assertEqual(response.status_code, 401)
        self.assertEqual(response.data, {
            "detail": "No active account found with the given credentials"})

        self.client.post('http://127.0.0.1:9000/api/auth/v1/users/',
                         data={
                             "email": "ilya@mail.ru",
                             "username": "admin",
                             "password": "123456lid"
                         })

        response = self.client.post(
            'http://127.0.0.1:9000/api/auth/v1/jwt/create/',
            data={
                "username": "admin",
                "password": "123456lis"
            })
        self.assertEqual(response.status_code, 401)
        self.assertEqual(response.data, {
            "detail": "No active account found with the given credentials"})

        response = self.client.post(
            'http://127.0.0.1:9000/api/auth/v1/jwt/create/',
            data={
                "username": "admin",
                "password": "123456lid"
            })
        self.assertEqual(response.status_code, 200)
        self.assertContains(response, 'refresh', status_code=200)
        self.assertContains(response, 'access', status_code=200)
