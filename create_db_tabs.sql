-- his_db数据库
DROP DATABASE IF EXISTS his_db;
CREATE DATABASE his_db CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
USE his_db;

-- 用户表users
create table users (
	id int(11) auto_increment primary key,
	username varchar(64),
	password varchar(64),
);

-- 科室表department
create table department (
  id int(9) auto_increment primary key,
  deptName varchar(64),
  delmark int(1) default 1
);
insert into department values(1,"内科",1);
insert into department values(2,"外科",1);
insert into department values(3,"儿科",1);
insert into department values(4,"肿瘤科",1);
insert into department values(5,"中医科",1);
insert into department values(6,"妇产科",1);
insert into department values(7,"麻醉科",1);
insert into department values(8,"精神科",1);
insert into department values(9,"影像科",1);
insert into department values(10,"其他科",1);

-- 挂号级别表registLevel
create table registLevel (
  id int(9) auto_increment primary key,
  registName varchar(64),
  registFee decimal(8, 2),
  registQuota int(5),
  delmark int(1) default 1
);
insert into registLevel values(1,"普通就诊",10,100,1);
insert into registLevel values(2,"专病就诊",30,100,1);
insert into registLevel values(3,"专家就诊",50,50,1);
insert into registLevel values(4,"特需就诊",100,20,1);

-- 结算方式表registMethod
create table registMethod (
  id int(9) auto_increment primary key,
  methodName varchar(64),
  delmark int(1) default 1
);
insert into registMethod values(1,"现金支付",1);
insert into registMethod values(2,"微信支付",1);
insert into registMethod values(3,"支付宝支付",1);
insert into registMethod values(4,"医保卡支付",1);
insert into registMethod values(5,"银联支付",1);

-- 医院员工表employee
create table employee (
  id int(9) auto_increment primary key,
  deptmentId int(9),
  registLevelId int(9),
  scheduling_id int(9),
	noon varchar(6),
  realName varchar(64),
  delmark int(1) default 1,
  foreign key (deptmentId) references department(id),
  foreign key (registLevelId) references regist_level(id),
);

-- 患者历次挂号信息表register
create table register (
  id int(9) auto_increment primary key,
  caseNumber varchar(64),
  realName varchar(64),
  gender varchar(6),
  cardNumber varchar(18),
  birthdate date,
  age int(3),
  homeAddress varchar(64),
  visitDate varchar(64),
  noon varchar(6),
  deptmentId int(9),
  employeeId int(9),
  registLevelId int(9),
  registMethodId int(9),
  isBook char(2),
	registMoney int(9),
	visitState int(1) default 1,
  foreign key (deptmentId) references department(id),
  foreign key (employeeId) references employee(id),
  foreign key (registLevelId) references regist_level(id),
  foreign key (registMethodId) references regist_method(id)
);

-- 医技项目表medicalTechnology
create table medicalTechnology (
  id int(9) primary key auto_increment,
  techName varchar(64),
  techPrice decimal(8,2)
);

-- 处置申请表disposalRequest
create table disposalRequest (
  id int(9) primary key auto_increment,
  registerId int(9),
  medicalTechnologyId int(9),
  creationTime varchar(64),
	disposalState int(9),
	expenseState int(9),
	foreign key (registerId) references register(id),
  foreign key (medicalTechnologyId) references medicalTechnology(id)
);

-- 药品信息表drugInfo
create table drugInfo (
  id int(9) primary key auto_increment,
  drugCode varchar(255),
  drugName varchar(255),
  drugFormat varchar(255),
  manufacturer varchar(255),
  drugType varchar(64),
  drugPrice decimal(8,2),
  creationDate date,
	quantity int(20)
);
insert into druginfo values (1, 'YpCode001', '药品1', '125g', '制药公司B', '处方药', 86, '2023-07-01', 300000);
insert into druginfo values (2, 'YpCode002', '药品2', '250g', '制药公司A', '非处方药', 32, '2023-07-01', 300000);
insert into druginfo values (3, 'YpCode003', '药品3', '200g', '制药公司C', '西药', 71, '2023-07-01', 300000);
insert into druginfo values (4, 'YpCode004', '药品4', '500g', '制药公司B', '处方药', 23, '2023-07-01', 300000);
insert into druginfo values (5, 'YpCode005', '药品5', '500g', '制药公司A', '非处方药', 51, '2023-07-01', 300000);
insert into druginfo values (6, 'YpCode006', '药品6', '100g', '制药公司D', '处方药', 92, '2023-07-01', 300000);
insert into druginfo values (7, 'YpCode007', '药品7', '200g', '制药公司C', '非处方药', 77, '2023-07-01', 300000);
insert into druginfo values (8, 'YpCode008', '药品8', '125g', '制药公司B', '处方药', 68, '2023-07-01', 300000);
insert into druginfo values (9, 'YpCode009', '药品9', '250g', '制药公司C', '西药', 52, '2023-07-01', 300000);
insert into druginfo values (10, 'YpCode010', '药品10', '250g', '制药公司A', '处方药', 26, '2023-07-01', 300000);
insert into druginfo values (11, 'YpCode011', '药品11', '500g', '制药公司B', '非处方药', 38, '2023-07-01', 300000);
insert into druginfo values (12, 'YpCode012', '药品12', '100g', '制药公司C', '处方药', 89, '2023-07-01', 300000);
insert into druginfo values (13, 'YpCode013', '药品13', '200g', '制药公司D', '西药', 49, '2023-07-01', 300000);
insert into druginfo values (14, 'YpCode014', '药品14', '100g', '制药公司A', '非处方药', 13, '2023-07-01', 300000);
insert into druginfo values (15, 'YpCode015', '药品15', '125g', '制药公司C', '处方药', 79, '2023-07-01', 300000);
insert into druginfo values (16, 'YpCode016', '药品16', '100g', '制药公司B', '西药', 42, '2023-07-01', 300000);
insert into druginfo values (17, 'YpCode017', '药品17', '500g', '制药公司C', '非处方药', 65, '2023-07-01', 300000);
insert into druginfo values (18, 'YpCode018', '药品18', '250g', '制药公司A', '处方药', 27, '2023-07-01', 300000);
insert into druginfo values (19, 'YpCode019', '药品19', '250g', '制药公司B', '西药', 59, '2023-07-01', 300000);
insert into druginfo values (20, 'YpCode020', '药品20', '125g', '制药公司C', '处方药', 94, '2023-07-01', 300000);

-- 处方表prescription
create table prescription (
  id int(9) primary key auto_increment,
  registerId int(9),
  drugId int(9),
  drugNumber int(20),
  creationTime datetime,
	expenseState int(9),
  drugState int(9),
  foreign key (registerId) references register(id),
  foreign key (drugId) references drugInfo(id)
);

-- 药品日志表prescriptionLog
create table prescriptionLog (
	id int(9) auto_increment primary key,
	registerId int(9),
	drugId int(9),
	drugNumber int(20),
	Time datetime,
	operation varchar(64),
	foreign key (registerId) references register(id),
  foreign key (drugId) references drugInfo(id)
)