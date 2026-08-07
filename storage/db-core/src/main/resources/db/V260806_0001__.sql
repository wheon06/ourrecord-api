ALTER TABLE user_device
    DROP CONSTRAINT udx_user_device_user_id_install_id;

ALTER TABLE user_device
    DROP COLUMN install_id,
    DROP COLUMN app_version,
    DROP COLUMN revoked_at;

ALTER TABLE user_device
    RENAME COLUMN push_token TO push_key;

ALTER TABLE user_device
    ALTER COLUMN push_key TYPE TEXT;
