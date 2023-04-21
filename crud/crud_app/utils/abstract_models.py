from django.db import models


class ModelTimeMixin(models.Model):
    created = models.DateTimeField(
        'Created',
        auto_now_add=True
    )
    modified = models.DateTimeField(
        'Modified',
        auto_now=True
    )

    class Meta:
        abstract = True


class HashMixin(models.Model):
    req_hash = models.CharField(
        'sha512 hash',
        max_length=128,
        blank=True,
    )

    class Meta:
        abstract = True
