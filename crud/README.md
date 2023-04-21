# CRUD Service

Swagger auto documentation URL (check and try requests): *.../swagger/*\
For dev server: http://localhost:9000/swagger/

____

Start development:

```
sh build.dev.sh
sh run.dev.sh
```

____

Test backend:
```
sh test.crud.sh
```

Stop all docker containers:
```
sh stop_dockers.sh
```

____

### Idempotentcy for create method

According to the REST documentation the POST method is not idempotent.

In the context of this task idempotentcy of create method may be 
considered in 2 approaches:
1. If we want to guarantee that the objects with the same data will not be
created. Than when receiving data we will calculate a unique body hash and
check if ny objects with the same hash were created. 
This approach is implemented.
2. In the second case the source would be responsible for generating a 
unique UUID and sending as a parameter. So really the same queries from 
the same source will take no effect, but the same body from different
sources will affect system by creating 2 objects.
I like this approach more, but for this task have pocked the first

The probability of the collisions (the same hash/UUID from two different 
objects) in both approaches is low enough to be considered as not sufficient 
and to be ignored.