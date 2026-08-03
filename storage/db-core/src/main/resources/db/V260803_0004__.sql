ALTER TABLE space_invite DROP CONSTRAINT space_invite_state_check;
ALTER TABLE space_invite ADD CONSTRAINT space_invite_state_check
    CHECK (state IN ('PENDING', 'ACCEPTED', 'EXPIRED'));
