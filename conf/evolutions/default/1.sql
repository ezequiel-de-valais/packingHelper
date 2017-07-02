# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table insumo (
  id                        bigint not null,
  nombre                    varchar(255),
  stock                     integer,
  constraint pk_insumo primary key (id))
;

create table producto (
  id                        bigint not null,
  nombre                    varchar(255),
  descripcion               varchar(500),
  constraint pk_producto primary key (id))
;

create table venta (
  id                        bigint not null,
  comprador                 varchar(255),
  producto_id               bigint not null,
  cantidad                  integer,
  constraint pk_venta primary key (id))
;


create table producto_insumo (
  producto_id                    bigint not null,
  insumo_id                      bigint not null,
  constraint pk_producto_insumo primary key (producto_id, insumo_id))
;
create sequence insumo_seq;

create sequence producto_seq;

create sequence venta_seq;

alter table venta add constraint fk_venta_producto_1 foreign key (producto_id) references producto (id) on delete restrict on update restrict;
create index ix_venta_producto_1 on venta (producto_id);



alter table producto_insumo add constraint fk_producto_insumo_producto_01 foreign key (producto_id) references producto (id) on delete restrict on update restrict;

alter table producto_insumo add constraint fk_producto_insumo_insumo_02 foreign key (insumo_id) references insumo (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists insumo;

drop table if exists producto_insumo;

drop table if exists producto;

drop table if exists venta;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists insumo_seq;

drop sequence if exists producto_seq;

drop sequence if exists venta_seq;

