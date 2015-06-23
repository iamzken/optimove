
    create table "OPTIMOVE"."OPTIMOVEDEMO"(
        "ID" VARCHAR2(20) not null,
       "NAME" VARCHAR2(100),
       "ADDRESS" VARCHAR2(200),
       "TELEPHONE" VARCHAR2(50),
       "LOCATION" SDO_GEOMETRY unique,
        constraint "PK_SPATIAL" primary key ("ID")
    );

    create unique index "OPTIMOVE"."PK_SPATIAL" on "OPTIMOVE"."OPTIMOVEDEMO"("ID");
    create index "OPTIMOVE"."OPTIMOVE_IDX" on "OPTIMOVE"."OPTIMOVEDEMO"("LOCATION");