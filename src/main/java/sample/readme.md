# Hexagonal Architecture Guide

## Part 1
* WebReq -> AppReq
* AppRes -> WebRes
1. adapter.in.web.dto
   1. WebRequest
   2. WebResponse
2. port.in.dto
   1. AppRequest
   2. AppResponse
3. adapter.in.web.mapper
   1. WebMapper
   2. Strategy
      1. WebStrategy

## Part 2
* AppReq -> Command
* Domain -> AppRes
1. domain
2. domain.command
   1. addCommand
3. application.mapper
   1. AppMapper
   2. Strategy
      1. AppStrategy

## Part 3
* Domain -> JpaEntity
* JpaEntity -> Domain
1. port.out
   1. CreatePort
   2. ReadPort
   3. UpdatePort
2. out.persistence
   1. PersistenceAdapter
   2. entity
      1. JpaEntity
   3. repository
      1. Repository
   4. mapper
      1. PersistenceMapper
      2. Strategy
         1. DomainStrategy

## Part 4
1. port.in
   1. UseCase
2. application
   1. UseCaseImpl
3. adapter.in.web
   1. ApiV1Controller
