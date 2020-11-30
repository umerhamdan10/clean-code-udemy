package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

public class UserLoginCheckerMyRefactoring {

    public static final int MAXIMUM_LOCK_PERIOD_IN_MS = 60 * 60 * 1000;

    /**
     * {@inheritDoc}.
     */
    public Lock isUserAllowedToLogin(long id, String status, boolean isFirstScreen, User userTryingToLogin, List existingLocks) {

        if (existingLocks.size() == 0 && existingLocks.get(0) == null) {
            return createWriteLock();
        }

        Object[] existingLockObject = (Object[]) existingLocks.get(0);
        String userIdWithLock = (String) existingLockObject[0];
        Date lockTimestamp = (Date) existingLockObject[1];

        if (userIdWithLock == null) {
            return createWriteLock();
        }

        if (userIdWithLock.equalsIgnoreCase(userTryingToLogin.getUserId())) {
            return createWriteLock();
        }

        long timeElapsedSinceLock = new Date().getTime() - lockTimestamp.getTime();

        if (isFirstScreen && timeElapsedSinceLock > MAXIMUM_LOCK_PERIOD_IN_MS) {
            return createWriteLock();
        }

        return createReadLockWithMessage(userIdWithLock);

    }

    Lock createReadLockWithMessage(String userIdWithLock) {
        String lockMsg = Constants.LOCK_TEXT.replaceAll("@@USER@@", userIdWithLock);
        Lock lock = new Lock();
        lock.setRead(true);
        //Only read access is permitted to other userTryingToLogin
        lock.setLockReason(lockMsg);
        return lock;
    }

    Lock createWriteLock() {
        Lock lock = new Lock();
        lock.setRead(false);
        return lock;
    }
}