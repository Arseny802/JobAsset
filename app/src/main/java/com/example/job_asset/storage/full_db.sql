------------------------
-------- TABLES --------
------------------------

CREATE TABLE IF NOT EXISTS job_asset.jobs (
	'id' INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT,
	'dateCreation' DATE NULL,

	'title' TEXT NOT NULL,
	'periodTarget' INTEGER NOT NULL,
	'priority' INTEGER NOT NULL,

	'repeatable' BOOLEAN NOT NULL DEFAULT FALSE,

	'movable' BOOLEAN NOT NULL DEFAULT TRUE,
	'movablePeriodMask' INTEGER NULL DEFAULT 0xFFFFFFFF,

	'description' TEXT NULL,
	'dateTarget' DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS job_asset.jobs_active (
	'id' INTEGER PRIMARY KEY,
	FOREIGN KEY(id) REFERENCES jobs(id)
);
CREATE TABLE IF NOT EXISTS job_asset.jobs_archive (
	'id' INTEGER PRIMARY KEY,
	FOREIGN KEY(id) REFERENCES jobs(id)
);

CREATE TABLE IF NOT EXISTS job_asset.jobs_deleting (
	'id' INTEGER PRIMARY KEY,
	FOREIGN KEY(id) REFERENCES jobs(id)
);

CREATE TABLE IF NOT EXISTS job_asset.user_settings (
	'id' INTEGER PRIMARY KEY AUTOINCREMENT,
	'key' TEXT UNIQUE,
	'value' TEXT,
	'dateUpdate' DATE
);

------------------------
------ PROCEDURES ------
------------------------

