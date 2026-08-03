ALTER TABLE user_identity DROP CONSTRAINT user_identity_provider_type_check;
ALTER TABLE user_identity ADD CONSTRAINT user_identity_provider_type_check
    CHECK (provider_type IN ('KAKAO', 'DEBUG_DEV'));
