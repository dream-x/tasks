import io

import pandas as pd
from django.db import models

from utils.abstract_models import ModelTimeMixin, HashMixin

AGES = (
    ('M', 'Mature'),
    ('C', 'Children'),
)


class Book(
    ModelTimeMixin,
    HashMixin,
):
    title = models.CharField(max_length=200)
    author = models.CharField(max_length=100)
    age_rating = models.CharField(
        max_length=1,
        choices=AGES,
    )
    is_available = models.BooleanField()
    description = models.TextField()

    def __str__(self):
        return self.title

    class Meta:
        verbose_name = 'Contact'
        verbose_name_plural = 'Contacts'

    @staticmethod
    def create_xls() -> io.BytesIO:
        """ Creates xls with all Book models """

        queryset = Book.objects.all()
        data = list(queryset.values())
        df = pd.DataFrame(data)

        if queryset:
            df = df.drop('req_hash', axis=1)
            df['created'] = df['created'].dt.strftime('%Y-%m-%d %H:%M:%S')
            df['modified'] = df['modified'].dt.strftime('%Y-%m-%d %H:%M:%S')

        towrite = io.BytesIO()
        df.to_excel(towrite)
        towrite.seek(0)

        return towrite
