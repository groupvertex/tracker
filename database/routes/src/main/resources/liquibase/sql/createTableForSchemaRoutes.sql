CREATE TABLE IF NOT EXISTS routes.route(
  id   SERIAL      NOT NULL CONSTRAINT second_key PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS routes.waypoint(
  id       SERIAL  NOT NULL CONSTRAINT third_key PRIMARY KEY,
  track_id INTEGER NOT NULL REFERENCES routes.route (id) ON DELETE CASCADE ON UPDATE CASCADE ,
  x        FLOAT   NOT NULL,
  y        FLOAT   NOT NULL,
  height   INTEGER NOT NULL,
  accuracy INTEGER,
  get_time TIMESTAMP WITHOUT TIME ZONE DEFAULT (now() AT TIME ZONE 'utc')
);