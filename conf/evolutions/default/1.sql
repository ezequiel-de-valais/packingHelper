# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table insumo (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  stock                     integer,
  constraint pk_insumo primary key (id))
;

create table producto (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  descripcion               varchar(500),
  constraint pk_producto primary key (id))
;

create table venta (
  id                        bigint auto_increment not null,
  comprador                 varchar(255),
  item_producto_id          bigint,
  item_nombre               varchar(255),
  cantidad                  integer,
  constraint pk_venta primary key (id))
;

create table viajero (
  id                        bigint auto_increment not null,
  usuario                   varchar(255),
  password                  varchar(255),
  fecha_de_nacimiento       datetime,
  sexo                      varchar(6),
  constraint ck_viajero_sexo check (sexo in ('Hombre','Mujer')),
  constraint pk_viajero primary key (id))
;


create table producto_insumo (
  producto_id                    bigint not null,
  insumo_id                      bigint not null,
  constraint pk_producto_insumo primary key (producto_id, insumo_id))
;



alter table producto_insumo add constraint fk_producto_insumo_producto_01 foreign key (producto_id) references producto (id) on delete restrict on update restrict;

alter table producto_insumo add constraint fk_producto_insumo_insumo_02 foreign key (insumo_id) references insumo (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table insumo;

drop table producto_insumo;

drop table producto;

drop table venta;

drop table viajero;

SET FOREIGN_KEY_CHECKS=1;

