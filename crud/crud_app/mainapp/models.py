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
