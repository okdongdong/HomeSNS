
CREATE TABLE `user` (
	`user_seq`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`user_id`	varchar(20)	NULL UNIQUE , 
	`user_password`	varchar(20)	NOT NULL,
	`user_email`	varchar(20)	NULL UNIQUE,
	`user_name`	varchar(20)	NOT NULL,
	`user_nickname`	varchar(20)	NULL,
	`user_phone`	varchar(20)	NULL UNIQUE,
	`user_bod`	date	NULL,
	`user_profile_image_url`	varchar(100)	NULL DEFAULT "image/noimage.png",
	`user_register_date`	date	NULL,
	`user_cls`	char(5)	NULL,
	`refresh_token`	varchar(50)	NULL
);

CREATE TABLE `feed` (
	`feed_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`group_id`	int	NOT NULL ,
	`feed_author`	int	NULL,
	`feed_title`	varchar(20)	NULL,
	`feed_content`	varchar(200)	NULL,
	`feed_upload_date`	date	NULL,
	`feed_event_date`	date	NULL,
	`feed_location`	varchar(20)	NULL
);

CREATE TABLE `group_code` (
	`group_code`	char(3) NOT NULL PRIMARY KEY,
	`group_code_name`	varchar(20)	NULL UNIQUE
);

CREATE TABLE `code` (
	`code`	char(2) NOT NULL PRIMARY KEY,
	`group_code`	char(3) NULL,
	`code_name`	varchar(20)	NULL UNIQUE,
	`use_yn`	varchar(1)	NULL DEFAULT "n"
);

CREATE TABLE `hashtag` (
	`hashtag_seq`	int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`feed_id`	int	NOT NULL,
	`hashtag_content`	varchar(20)	NOT NULL
);

CREATE TABLE `group` (
	`group_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`group_name`	varchar(20)	NULL,
	`group_profile_image_url`	varchar(100)	NULL DEFAULT "image/nogroupimage.png",
	`group_leader_seq`	varchar(20)	NOT NULL,
	`group_password`	varchar(20)	NULL
);

CREATE TABLE `group_member` (
	`group_id`	int	NOT NULL,
	`user_seq`	int	NOT NULL,
	`manager_yn`	char(1)	NULL DEFAULT "n"
);

CREATE TABLE `schedule` (
	`schedule_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`group_id`	int	NOT NULL,
	`schedule_title`	varchar(30)	NULL,
	`schedule_content`	varchar(100)	NULL,
	`schedule_date`	date	NOT NULL,
	`schedule_time`	time	NULL,
	`schedule_location`	varchar(50)	NULL
);

CREATE TABLE `comment` (
	`comment_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`feed_id`	int	NOT NULL,
	`comment_tag`	int	NULL,
	`comment_author`	int	NULL,
	`comment_content`	varchar(100)	NULL,
	`comment_upload_date`	date	NULL
);

CREATE TABLE `feed_emotion` (
	`feed_id`	int	NOT NULL ,
	`good`	int	NULL,
	`sad`	int	NULL,
	`check`	int	NULL,
	`fun`	int	NULL,
	`amaze`	int	NULL
);

CREATE TABLE `comment_emotion` (
	`comment_id`	int	NOT NULL,
	`good`	int	NULL,
	`sad`	int	NULL,
	`check`	int	NULL,
	`fun`	int	NULL,
	`amaze`	int	NULL
);

CREATE TABLE `file` (
	`file_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`feed_id`	int	NOT NULL,
	`file_name`	varchar(30)	NULL,
	`file_size`	int	NULL,
	`file_content_type`	varchar(20)	NULL,
	`file_url`	varchar(50)	NULL UNIQUE,
	`reg_dt`	date	NULL
);

CREATE TABLE `scrap` (
	`user_seq`	int	NOT NULL,
	`feed_id`	int	NOT NULL
);

CREATE TABLE `location` (
	`location_id`	int	NOT NULL PRIMARY KEY,
	`group_id`	int	NOT NULL,
	`location_name`	varchar(20)	NULL,
	`lat`	float	NULL,
	`lng`	float	NULL
);

CREATE TABLE `location_favorite` (
	`location_id`	int	NOT NULL  ,
	`user_seq`	int	NOT NULL
);

CREATE TABLE `vote` (
	`vote_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`group_id`	int	NOT NULL,
	`user_seq`	int	NOT NULL,
	`vote_name`	varchar(20)	NULL
);

CREATE TABLE `vote_item` (
	`vote_item_name`	varchar(30)	NOT NULL PRIMARY KEY,
	`vote_id`	int	NOT NULL,
	`vote_cnt`	int	NULL
);

CREATE TABLE `voter` (
	`vote_id`	int	NOT NULL,
	`vote_item_name`	varchar(20)	NOT NULL,
	`user_seq`	int	NOT NULL
);

CREATE TABLE `feed_emotion_user_use` (
	`user_seq`	int	NOT NULL,
	`feed_id`	int	NOT NULL,
	`emotion_code`	char(5)	NULL
);

CREATE TABLE `event_member` (
	`feed_id`	int	NOT NULL,
	`user_seq`	int	NOT NULL
);

CREATE TABLE `comment_emotion_user_use` (
	`comment_id`	int	NOT NULL,
	`user_seq`	int	NOT NULL,
	`emotion_code`	char(5)	NULL
);


ALTER TABLE `feed` ADD CONSTRAINT `FK_group_TO_feed_1` FOREIGN KEY (
	`group_id`
)
REFERENCES `group` (
	`group_id`
);

ALTER TABLE `code` ADD CONSTRAINT `FK_group_code_TO_code_1` FOREIGN KEY (
	`group_code`
)
REFERENCES `group_code` (
	`group_code`
);

ALTER TABLE `hashtag` ADD CONSTRAINT `FK_feed_TO_hashtag_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
);

ALTER TABLE `group_member` ADD CONSTRAINT `FK_group_TO_group_member_1` FOREIGN KEY (
	`group_id`
)
REFERENCES `group` (
	`group_id`
);

ALTER TABLE `group_member` ADD CONSTRAINT `FK_user_TO_group_member_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `schedule` ADD CONSTRAINT `FK_group_TO_schedule_1` FOREIGN KEY (
	`group_id`
)
REFERENCES `group` (
	`group_id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_feed_TO_comment_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
);

ALTER TABLE `feed_emotion` ADD CONSTRAINT `FK_feed_TO_feed_emotion_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
);

ALTER TABLE `comment_emotion` ADD CONSTRAINT `FK_comment_TO_comment_emotion_1` FOREIGN KEY (
	`comment_id`
)
REFERENCES `comment` (
	`comment_id`
);

ALTER TABLE `file` ADD CONSTRAINT `FK_feed_TO_file_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
);

ALTER TABLE `scrap` ADD CONSTRAINT `FK_user_TO_scrap_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `scrap` ADD CONSTRAINT `FK_feed_TO_scrap_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
);

ALTER TABLE `location` ADD CONSTRAINT `FK_group_TO_location_1` FOREIGN KEY (
	`group_id`
)
REFERENCES `group` (
	`group_id`
);

ALTER TABLE `location_favorite` ADD CONSTRAINT `FK_location_TO_location_favorite_1` FOREIGN KEY (
	`location_id`
)
REFERENCES `location` (
	`location_id`
);

ALTER TABLE `location_favorite` ADD CONSTRAINT `FK_user_TO_location_favorite_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `vote` ADD CONSTRAINT `FK_group_TO_vote_1` FOREIGN KEY (
	`group_id`
)
REFERENCES `group` (
	`group_id`
);

ALTER TABLE `vote` ADD CONSTRAINT `FK_user_TO_vote_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `vote_item` ADD CONSTRAINT `FK_vote_TO_vote_item_1` FOREIGN KEY (
	`vote_id`
)
REFERENCES `vote` (
	`vote_id`
);

ALTER TABLE `voter` ADD CONSTRAINT `FK_vote_item_TO_voter_1` FOREIGN KEY (
	`vote_id`
)
REFERENCES `vote_item` (
	`vote_id`
);

ALTER TABLE `voter` ADD CONSTRAINT `FK_vote_item_TO_voter_2` FOREIGN KEY (
	`vote_item_name`
)
REFERENCES `vote_item` (
	`vote_item_name`
);

ALTER TABLE `voter` ADD CONSTRAINT `FK_user_TO_voter_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `feed_emotion_user_use` ADD CONSTRAINT `FK_user_TO_feed_emotion_user_use_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `feed_emotion_user_use` ADD CONSTRAINT `FK_feed_TO_feed_emotion_user_use_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
);

ALTER TABLE `event_member` ADD CONSTRAINT `FK_feed_TO_event_member_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
);

ALTER TABLE `event_member` ADD CONSTRAINT `FK_user_TO_event_member_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `comment_emotion_user_use` ADD CONSTRAINT `FK_comment_TO_comment_emotion_user_use_1` FOREIGN KEY (
	`comment_id`
)
REFERENCES `comment` (
	`comment_id`
);

ALTER TABLE `comment_emotion_user_use` ADD CONSTRAINT `FK_user_TO_comment_emotion_user_use_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

