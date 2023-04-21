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
