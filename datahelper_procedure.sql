use datahelper;
drop procedure if exists proc_usernameverify;
DELIMITER $
  CREATE PROCEDURE proc_usernameverify(IN inputname varchar(16))
    BEGIN
      SELECT * FROM users WHERE username=inputname;
    END $
DELIMITER ;