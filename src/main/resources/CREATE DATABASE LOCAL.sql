-- Database: TaskTrackerDB

-- DROP DATABASE IF EXISTS "TaskTrackerDB";

/*
CREATE DATABASE "TaskTrackerDB"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
*/

CREATE TABLE Users -- Таблица пользователей
(
	UserName CHARACTER VARYING(30) NOT NULL PRIMARY KEY, -- Имя пользователя - не уникальное
	UserPassword CHARACTER VARYING(60) NOT NULL -- Пароль пользователя
);

INSERT INTO Users (UserName, UserLogin, UserPassword) VALUES ('Irina', 'Irina6404', '6404');

CREATE TABLE UserGroups -- Таблица групп
(
	GroupID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, -- ID группы - уникальный (ПК)
	GroupName CHARACTER VARYING(30), -- Имя группы - не уникальное (проверка на NOT NULL будет на фронте)
	GroupPassword CHARACTER VARYING(30) -- Пароль для входа в группу, группа может быть без пароля, в этом случае это поле равно NULL
);

INSERT INTO UserGroups (GroupName) VALUES ('GigaGroup');

INSERT INTO UserGroups (GroupID, GroupName) VALUES (0, NULL);

/*
Таблица принадлжености пользователей к группам. Каждая строка содержит значения:
UserName - логин пользователя (ВК - ссылается на таблицу пользователей на UserLogin);
GroupID - ID группы, к которой пользователь принадлежит (ВК - ссылается на таблицу групп на GroupID);
*/
CREATE TABLE UserAndGroup
(
	UserLogin CHARACTER VARYING(30) REFERENCES users (UserLogin) ON DELETE CASCADE,
	GroupID INTEGER REFERENCES usergroups (GroupID) ON DELETE CASCADE,
	PRIMARY KEY (UserName, GroupID) -- Составной ПК - состоит из логина пользователя и ID группы
);

CREATE TABLE Tasks -- Таблица задач
(
	UserTaskID SERIAL PRIMARY KEY, -- ID задачи
	CreatorLogin CHARACTER VARYING(30) NOT NULL, -- Логин пользователя, создавшего задачу
	CreatorGroupID INTEGER NOT NULL, -- ID группы, в которой состоит пользователь-создатель задачи
	TaskName CHARACTER VARYING(30) NOT NULL,
	TaskDescription CHARACTER VARYING(30),
	TaskPriority INTEGER NOT NULL DEFAULT 0 CHECK (TaskPriority IN (0, 1, 2)),
	TaskStatus BOOLEAN DEFAULT '0',
	TaskDeadline TIMESTAMP WITH TIME ZONE DEFAULT (NOW() + interval '1 day'),
	/*
	Составной ВК - ссылается на ПК таблицы UserAndGroup, позволяя иметь доступ как к создателю задачи, так и к его группе.
	*/
	FOREIGN KEY (CreatorLogin, CreatorGroupID) REFERENCES UserAndGroup(UserName, GroupID) ON DELETE CASCADE
);
