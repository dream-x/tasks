from rest_framework import routers
from .views import BooksViewSet

router = routers.SimpleRouter()

router.register('', BooksViewSet, basename='books_view')

urlpatterns = router.urls
