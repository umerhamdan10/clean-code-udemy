package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

public class UserLoginChecker {

    /**
     * {@inheritDoc}.
     */
    public Lock isUserAllowedToLogin(long id, String status, boolean isFirstScreen, User userTryingToLogin, List existingLocks) {
        Date time = new Date();
        Lock lck = new Lock();
        if (existingLocks.size() > 0 && existingLocks.get(0) != null) {
            Object[] object = (Object[]) existingLocks.get(0);
            String userId = (String) object[0];
            Date lockTimestamp = (Date) object[1];
            if (userId != null) {
                // message which is shown to the userTryingToLogin
                String lockMsg = Constants.LOCK_TEXT.replaceAll("@@USER@@",
                        userId);
                //if userID is present, the Lock time stamp will also be present
                //4800000 milliseconds equals to 1 1/2 hours.
                if (time.getTime() - lockTimestamp.getTime() > 3600000) {
                    //New userTryingToLogin gets lock only on first screen
                    //If 1 1/2 hours expires when userTryingToLogin is not on 1st screen then for same userTryingToLogin lock can be refreshed.
                    if (isFirstScreen
                            || userId.equalsIgnoreCase(userTryingToLogin.getUserId())) {
                        //to set the  access to write mode
                        lck.setRead(false);
                        return lck;
                    }
                    lck.setRead(true);
                    //Only read access is permitted to other userTryingToLogin
                    lck.setLockReason(lockMsg);
                    return lck;
                } else if (userId.equalsIgnoreCase(userTryingToLogin.getUserId())) {
                    // Locked By Same User, Write access
                    lck.setRead(false);
                    return lck;
                } else {
                    lck.setRead(true);
                    //Only Read Access is Permitted
                    lck.setLockReason(lockMsg);
                    return lck;
                }
            }
        }
        lck.setRead(false);
        return lck;
    }
}