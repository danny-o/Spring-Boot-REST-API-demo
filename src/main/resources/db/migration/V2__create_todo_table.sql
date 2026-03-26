CREATE TABLE todo (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    description VARCHAR(255),
    target_date DATE,
    priority INTEGER DEFAULT 1,
    done BOOLEAN default false
)