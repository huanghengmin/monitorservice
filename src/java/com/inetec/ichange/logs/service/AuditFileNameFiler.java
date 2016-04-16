package com.inetec.ichange.logs.service;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2007-12-18
 * Time: 21:41:39
 * To change this template use File | Settings | File Templates.
 */
public class AuditFileNameFiler implements FileFilter {
    public boolean accept(File dir) {
        return LogSysCacheProcess.existSubString(dir.getAbsolutePath(), LogSysCacheProcess.Str_FilePrix);
    }
}
