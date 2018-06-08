use datahelper;
show triggers;

drop trigger if exists tri_request_title;
drop trigger if exists tri_request_content;

DELIMITER $
create trigger tri_request_title before insert on requests FOR EACH ROW
BEGIN
if new.request_title LIKE '%敏感词%' then
	set new.request_title = replace(new.request_title, "敏感词", "[和谐]");
END IF;
END $
DELIMITER;

DELIMITER $
create trigger tri_request_content before insert on requests FOR EACH ROW
BEGIN
if new.request_content LIKE '%敏感词%' then
	set new.request_content = replace(new.request_content, "敏感词", "[和谐]");
END IF;
END $
DELIMITER ;
