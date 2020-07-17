--liquibase formatted sql 
--changeset ryanhhtan:createStoredProcedure_setUserType endDelimiter:!!
DROP PROCEDURE IF EXISTS setUserType!!
CREATE PROCEDURE setUserType(IN _id INT, IN _type VARCHAR(50))
BEGIN
  DECLARE doctorId INT; 
  DECLARE patientId INT; 
  DECLARE currentType VARCHAR(50);
  START TRANSACTION;
  SELECT type INTO currentType FROM users WHERE id=_id;
  IF currentType = 'unspecified' THEN
    UPDATE users set type=_type WHERE id=_id;
  ELSEIF currentType <> _type THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Can not change finalized user type.';
  END IF;
  COMMIT;
  CASE _type
    WHEN 'doctor' THEN
      SELECT id INTO doctorId FROM doctors WHERE id=_id;
      IF doctorId IS NULL THEN
        INSERT INTO doctors(id) VALUES(_id);
      END IF;
    WHEN 'patient' THEN
      SELECT id INTO patientId FROM patients WHERE id=_id;
      IF patientId IS NULL THEN
        INSERT INTO patients(id) VALUES(_id);
      END IF;
  END CASE;
END!!
--rollback DROP PROCEDURE IF EXISTS setUserType;
