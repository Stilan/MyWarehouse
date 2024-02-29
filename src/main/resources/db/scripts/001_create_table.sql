-- liquibase formatted sql
--changeset aleksandrlitvinov:2

create table stock (
                       id uuid primary key,
                       isDeleted boolean,
                       name varchar(50)
);

create table product (
        id uuid primary key,
        article integer,
        isDeleted boolean,
        lastpurchaseprice integer,
        lastsaleprice integer,
        name varchar(50),
        remaininggoods integer,
        stockid uuid REFERENCES stock
);

create table stockproductquantity (
      id uuid primary key,
      quantity integer,
      productid uuid REFERENCES product,
      stockid uuid REFERENCES stock
);



