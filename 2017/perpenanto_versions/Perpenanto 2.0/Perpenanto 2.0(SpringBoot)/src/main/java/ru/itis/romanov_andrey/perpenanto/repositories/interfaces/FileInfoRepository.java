package ru.itis.romanov_andrey.perpenanto.repositories.interfaces;

import ru.itis.romanov_andrey.perpenanto.models.usermodels.FileInfo;

/**
 * 24.01.2018
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface FileInfoRepository extends CrudDAOInterface<FileInfo, Long>{

    FileInfo findByStorageFileName(String storageFileName);

}
