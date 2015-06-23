SELECT *      FROM optiMoveDemo     
 WHERE SDO_GEOM.SDO_DISTANCE(location,
 MDSYS.SDO_GEOMETRY(2001,8307,MDSYS.SDO_POINT_TYPE(,,0),
 NULL,NULL),1) null and (name like '%null%' or address like'%null%')
