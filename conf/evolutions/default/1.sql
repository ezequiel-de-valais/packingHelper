# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table elemento (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  peso_individual           integer,
  de_mano                   tinyint(1) default 0,
  consumible                tinyint(1) default 0,
  constraint pk_elemento primary key (id))
;

create table insumo (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  stock                     integer,
  constraint pk_insumo primary key (id))
;

create table item (
  id                        bigint auto_increment not null,
  elemento_id               bigint,
  cantidad                  integer,
  peso_individual           integer,
  de_mano                   tinyint(1) default 0,
  consumible                tinyint(1) default 0,
  viaje_id                  bigint,
  constraint pk_item primary key (id))
;

create table lugar (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  limite_equipaje_de_mano   integer,
  limite_equipaje_de_bodega integer,
  constraint pk_lugar primary key (id))
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

create table viaje (
  id                        bigint auto_increment not null,
  fecha_inicio              datetime,
  fecha_fin                 datetime,
  espacio_libre_en_vuelta   integer,
  viajero_id                bigint,
  destino_id                bigint,
  constraint pk_viaje primary key (id))
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
alter table item add constraint fk_item_elemento_1 foreign key (elemento_id) references elemento (id) on delete restrict on update restrict;
create index ix_item_elemento_1 on item (elemento_id);
alter table item add constraint fk_item_viaje_2 foreign key (viaje_id) references viaje (id) on delete restrict on update restrict;
create index ix_item_viaje_2 on item (viaje_id);
alter table viaje add constraint fk_viaje_viajero_3 foreign key (viajero_id) references viajero (id) on delete restrict on update restrict;
create index ix_viaje_viajero_3 on viaje (viajero_id);
alter table viaje add constraint fk_viaje_destino_4 foreign key (destino_id) references lugar (id) on delete restrict on update restrict;
create index ix_viaje_destino_4 on viaje (destino_id);



alter table producto_insumo add constraint fk_producto_insumo_producto_01 foreign key (producto_id) references producto (id) on delete restrict on update restrict;

alter table producto_insumo add constraint fk_producto_insumo_insumo_02 foreign key (insumo_id) references insumo (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table elemento;

drop table insumo;

drop table producto_insumo;

drop table item;

drop table lugar;

drop table producto;

drop table venta;

drop table viaje;

drop table viajero;

SET FOREIGN_KEY_CHECKS=1;

