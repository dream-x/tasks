from rest_framework import routers
from .views import BooksViewSet

app_name = 'mainapp'

router = routers.SimpleRouter()

router.register('books', BooksViewSet, basename='books')

urlpatterns = router.urls
