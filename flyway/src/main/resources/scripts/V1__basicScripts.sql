CREATE TABLE company
(
    `id`                BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Unique ID of the company',
    `name`              VARCHAR(255) NOT NULL COMMENT 'Name of the company',
    `parent_company_id` BIGINT       NULL COMMENT 'Parent Company ID',
    PRIMARY KEY (`id`),
    KEY `FK_COMPANY_PARENT_COMPANY_ID` (`parent_company_id`),
    CONSTRAINT `FK_COMPANY_PARENT_COMPANY_ID` FOREIGN KEY (`parent_company_id`) REFERENCES `company` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT 'Companies information';

CREATE TABLE station
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Unique ID of the station',
    `name`       VARCHAR(255) NOT NULL COMMENT 'Name of the station',
    `latitude`   DOUBLE       NOT NULL COMMENT 'Coordinates of the station (latitude)',
    `longitude`  DOUBLE       NOT NULL COMMENT 'Coordinates of the station (longitude)',
    `company_id` BIGINT       NOT NULL COMMENT 'ID of the company',
    PRIMARY KEY (`id`),
    KEY `FK_STATION_COMPANY_ID` (`company_id`),
    CONSTRAINT `FK_STATION_COMPANY_ID` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT 'Stations information';


INSERT INTO company (id, name, parent_company_id)
    VALUE (1, 'Apple', NULL);
INSERT INTO company (id, name, parent_company_id)
    VALUE (2, 'Amazon', NULL);
INSERT INTO company (id, name, parent_company_id)
    VALUE (3, 'Google', NULL);
INSERT INTO company (id, name, parent_company_id)
    VALUE (4, 'NeXT', 1);
INSERT INTO company (id, name, parent_company_id)
    VALUE (5, 'Siri Inc', 1);
INSERT INTO company (id, name, parent_company_id)
    VALUE (6, 'AuthenTec', 1);
INSERT INTO company (id, name, parent_company_id)
    VALUE (7, 'Mandiant', 3);
INSERT INTO company (id, name, parent_company_id)
    VALUE (8, 'Fitbit', 3);
INSERT INTO company (id, name, parent_company_id)
    VALUE (9, 'Waze', 3);

INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Apple Office 1', 40.123458, 11.675178, 1);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Apple Office 2', 50.541115, 15.661655, 1);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Apple Office 3', 15.661515, 85.132222, 1);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Apple Office 4', 85.561615, 45.465156, 1);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Apple Office 5', 49.111651, 12.551664, 1);

INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Amazon Office 1', 60.128745, 31.561665, 2);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Amazon Office 2', 41.325478, 19.223654, 2);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Amazon Office 3', 15.152364, 34.111552, 2);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Amazon Office 4', 10.195748, 63.322211, 2);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Amazon Office 5', 20.123741, 16.199665, 2);

INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Google Office 1', 50.258852, 10.123789, 3);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Google Office 2', 40.321123, 30.987412, 3);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Google Office 3', 30.123456, 87.859615, 3);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Google Office 4', 19.876545, 65.369874, 3);
INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Google Office 5', 22.156688, 54.564616, 3);

INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('NeXT Office 1', 11.556644, 88.564123, 4);

INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Siri Inc Office 1', 30.324789, 97.112874, 5);

INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('AuthenTec Office 1', 14.147896, 31.123698, 6);

INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Mandiant Office 1', 54.365478, 32.324569, 7);

INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Fitbit Office 1', 54.254789, 98.654789, 8);

INSERT INTO station (name, latitude, longitude, company_id)
    VALUE ('Waze Office 1', 10.523654, 31.256314, 9);

DELIMITER //
CREATE PROCEDURE distance_calculator(
    IN radius float,
    IN latitudeIN Decimal(8, 6),
    IN longitudeIN Decimal(8, 6)
)
BEGIN

    select id, latitude, longitude, name, company_id
    from station
    where (POWER(latitude - LatitudeIN, 2) + POWER(longitude - longitudeIN, 2)) <= POWER(radius, 2);

END
//
DELIMITER ;