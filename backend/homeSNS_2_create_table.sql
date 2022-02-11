
CREATE TABLE `user` (
	`user_seq`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`user_id`	varchar(50)	NULL UNIQUE , 
	`user_password`	varchar(300)	NOT NULL,
	`user_email`	varchar(50)	NULL UNIQUE,
	`user_name`	varchar(50)	NOT NULL,
	`user_nickname`	varchar(50)	NULL,
	`user_phone`	varchar(50)	NULL UNIQUE,
	`user_bod`	date	NULL,
	`user_profile_image_url`	varchar(300)	NULL DEFAULT "image/noimage.png",
	`user_register_date`	timestamp	NULL,
	`user_cls`	char(5)	NULL,
	`refresh_token`	varchar(50)	NULL
);

CREATE TABLE `feed` (
	`feed_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`group_id`	int	NOT NULL ,
	`feed_author`	int	NULL,
	`feed_title`	varchar(50)	NULL,
	`feed_content`	varchar(1000)	NULL,
	`feed_upload_date`	timestamp	NULL,
	`feed_event_date`	datetime	NULL,
	`feed_location_id` int	NULL
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
	`hashtag_content`	varchar(50)	NOT NULL
);

CREATE TABLE `group_list` (
	`group_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`group_name`	varchar(50)	NULL,
	`group_profile_image_url`	varchar(300)	NULL DEFAULT "image/nogroupimage.png",
	`group_leader_seq`	int	NOT NULL,
	`group_password`	varchar(200)	NULL
);

CREATE TABLE `group_member` (
	`group_id`	int	NOT NULL,
	`user_seq`	int	NOT NULL,
	`manager_yn`	char(1)	NULL DEFAULT "n"
);

CREATE TABLE `schedule` (
    `schedule_id`    int    NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `group_id`    int    NOT NULL,
    `schedule_title`    varchar(50)    NULL,
    `schedule_content`    varchar(300)    NULL,
    `schedule_date_start`    date    NOT NULL,
    `schedule_date_end`    date    NOT NULL,
    `schedule_time_start`    time    NULL,
    `schedule_time_end`    time    NULL
);


CREATE TABLE `comment` (
	`comment_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`feed_id`	int	NOT NULL,
	`comment_tag`	int	NULL,
	`comment_author`	int	NULL,
	`comment_content`	varchar(300)	NULL,
	`comment_upload_date`	timestamp	NULL
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
	`file_name`	varchar(100)	NULL,
	`file_size`	float	NULL,
	`file_content_type`	varchar(50)	NULL,
	`file_url`	varchar(300)	NULL UNIQUE,
	`reg_dt`	timestamp	NULL
);

CREATE TABLE `scrap` (
	`user_seq`	int	NOT NULL,
	`feed_id`	int	NOT NULL
);

CREATE TABLE `location` (
	`location_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`group_id`	int	NOT NULL,
	`location_name`	varchar(100)	NULL,
	`lat`	float	NULL,
	`lng`	float	NULL
);

CREATE TABLE `location_favorite` (
	`location_id`	int	NOT NULL  ,
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

CREATE TABLE `game` (
	`game_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`group_id`	int	NOT NULL,
	`game_title`	varchar(50)	NOT NULL,
	`game_author`	int	NOT NULL,
	`game_register_date`	timestamp	NULL,
	`code` char(5)	NOT NULL,
	`update_yn`	varchar(1)	NULL DEFAULT "y"
);

CREATE TABLE `ghost_leg` (
	`game_id`	int	NOT NULL,
	`player`	varchar(100)	NOT NULL,
	`player_num`	int	NOT NULL,
	`result`	varchar(100)	NOT NULL,
	`map`	varchar(300)	NOT NULL
);

CREATE TABLE `vote_item` (
	`vote_item_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`vote_item_name`	varchar(50)	NOT NULL,
	`game_id`	int	NOT NULL,
	`count`	int	NOT NULL DEFAULT 0
);

CREATE TABLE `voter` (
	`vote_item_id`	int,
	`user_seq`	int	NOT NULL
);


CREATE TABLE `profile_image` (
    `profile_image_id`    int    NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`user_seq`     int,
    `group_id`    int,
    `profile_image_name`    varchar(50) NULL,
	`profile_image_size`    float NULL,
	`profile_image_url`    varchar(20)    NULL,
    `reg_dt`    timestamp    NULL
);
CREATE TABLE `notice` (
`notice_id`      int    NOT NULL PRIMARY KEY AUTO_INCREMENT,
`user_seq` int,
`target_user_seq` int,
`target_user_group_id` int,
`notice_type`      VARCHAR(50),
`notice_content_id` int,
`notice_read_yn` char(1) DEFAULT "n",
`notice_time` DATETIME
);

ALTER TABLE `feed` ADD CONSTRAINT `FK_group_TO_feed_1` FOREIGN KEY (
	`group_id`
)
REFERENCES `group_list` (
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
)
ON DELETE CASCADE;

ALTER TABLE `group_member` ADD CONSTRAINT `FK_group_TO_group_member_1` FOREIGN KEY (
	`group_id`
)
REFERENCES `group_list` (
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
REFERENCES `group_list` (
	`group_id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_feed_TO_comment_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
)
ON DELETE CASCADE;

ALTER TABLE `feed_emotion` ADD CONSTRAINT `FK_feed_TO_feed_emotion_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
)
ON DELETE CASCADE;

ALTER TABLE `comment_emotion` ADD CONSTRAINT `FK_comment_TO_comment_emotion_1` FOREIGN KEY (
	`comment_id`
)
REFERENCES `comment` (
	`comment_id`
)
ON DELETE CASCADE;

ALTER TABLE `file` ADD CONSTRAINT `FK_feed_TO_file_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
)
ON DELETE CASCADE;

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
)
ON DELETE CASCADE;

ALTER TABLE `location` ADD CONSTRAINT `FK_group_TO_location_1` FOREIGN KEY (
	`group_id`
)
REFERENCES `group_list` (
	`group_id`
);
ALTER TABLE  `feed` ADD CONSTRAINT `FK_location_TO_feed_1` FOREIGN KEY (
	`feed_location_id`
)
REFERENCES `location` (
	`location_id`
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


ALTER TABLE `game` ADD CONSTRAINT `FK_group_TO_game_1` FOREIGN KEY (
	`group_id`
)
REFERENCES `group_list` (
	`group_id`
);


ALTER TABLE `ghost_leg` ADD CONSTRAINT `FK_game_TO_ghost_leg_1` FOREIGN KEY (
	`game_id`
)
REFERENCES `game` (
	`game_id`
)
ON DELETE CASCADE;

ALTER TABLE `vote_item` ADD CONSTRAINT `FK_game_TO_vote_item_1` FOREIGN KEY (
	`game_id`
)
REFERENCES `game` (
	`game_id`
)
ON DELETE CASCADE;

ALTER TABLE `voter` ADD CONSTRAINT `FK_vote_item_TO_voter_1` FOREIGN KEY (
	`vote_item_id`
)
REFERENCES `vote_item` (
	`vote_item_id`
)
ON DELETE CASCADE;

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
)
ON DELETE CASCADE;

ALTER TABLE `event_member` ADD CONSTRAINT `FK_feed_TO_event_member_1` FOREIGN KEY (
	`feed_id`
)
REFERENCES `feed` (
	`feed_id`
)
ON DELETE CASCADE;

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
)
ON DELETE CASCADE;

ALTER TABLE `comment_emotion_user_use` ADD CONSTRAINT `FK_user_TO_comment_emotion_user_use_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

