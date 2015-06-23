
-- 创建测试用表
CREATE TABLE optiMoveDemo(
 "ID" VARCHAR2(20) NOT NULL,
 "NAME" VARCHAR2(100),
 "ADDRESS" VARCHAR2(200),
 "TELEPHONE" VARCHAR2(50),
 "LOCATION" "MDSYS"."SDO_GEOMETRY"
)LOGGING;

-- 创建主键约束
ALTER TABLE optiMoveDemo
 ADD CONSTRAINT "PK_SPATIAL" PRIMARY KEY("ID");




-- 根据用户表填写空间元数据
INSERT INTO USER_SDO_GEOM_METADATA
  VALUES(
  'optiMoveDemo',
  'location',
  MDSYS.SDO_DIM_ARRAY(
   MDSYS.SDO_DIM_ELEMENT('Longitude',-180,180,10),
   MDSYS.SDO_DIM_ELEMENT('Latitude',-90,90,10)
  ),
  8307
 );

-- 建立空间索引
CREATE INDEX optiMove_IDX
 ON OPTIMOVEDEMO(location)
 INDEXTYPE IS MDSYS.SPATIAL_INDEX;


-- 导入测试数据
INSERT INTO optiMoveDemo
 VALUES(
  'dbeb7ea11eaf2b53a9b7',
  '小肥羊(天河店)',
  '广州市天河区天寿路25号',
  '020-38217746',
  MDSYS.SDO_GEOMETRY(
   2001,
   8307,
   MDSYS.SDO_POINT_TYPE(113.3293658, 23.14338586, 0),
   NULL,
   NULL
  )
 );

INSERT INTO optiMoveDemo
 VALUES(
  'ef8393ef6273a72b2f70',
  '山东老家',
  '广州市越秀区合群一马路43号',
  '020-87778983',
  MDSYS.SDO_GEOMETRY(
   2001,
   8307,
   MDSYS.SDO_POINT_TYPE(113.2932474, 23.11883515, 0),
   NULL,
   NULL
  )
 );
 

-- 空间分析查询(113.2359818,23.16937253)周边十公里信息5条
SELECT
  B.id id, B.name name, B.dist dist
FROM (
  SELECT
    A.id id, A.name name, SDO_GEOM.SDO_DISTANCE(A.location,MDSYS.SDO_GEOMETRY(2001,8307,MDSYS.SDO_POINT_TYPE(113.2359818,23.16937253,0),NULL,NULL),1) dist
  FROM
    optiMoveDemo A
  WHERE
    SDO_WITHIN_DISTANCE(A.LOCATION,MDSYS.SDO_GEOMETRY(2001,8307,MDSYS.SDO_POINT_TYPE(113.2359818,23.16937253,0),NULL,NULL),'distance=9000') = 'TRUE'
  ORDER BY A.name
  ) B
