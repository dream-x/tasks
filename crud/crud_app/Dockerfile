FROM python:3.9 as base

MAINTAINER Ilya Buryan

RUN python -m venv /opt/venv
ENV PATH="/opt/venv/bin:$PATH"
ENV PYTHONUNBUFFERED 1

WORKDIR /code
COPY . /code/

RUN pip install -r requirements.txt

FROM base as DEV
CMD sh -c "python manage.py migrate \
 && python manage.py runserver $BACKEND_HOST:$BACKEND_PORT"

FROM base as PROD
CMD sh -c "python manage.py migrate \
 && gunicorn PricesParser.wsgi -w=4 --bind $BACKEND_HOST:$BACKEND_PORT --timeout 600"

EXPOSE $BACKEND_PORT