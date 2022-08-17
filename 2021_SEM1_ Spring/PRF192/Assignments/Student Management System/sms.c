#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <assert.h>
#include <conio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <windows.h>

#define FILE_NAME "studentRecordSystem.bin"
#define COURSE_FILE "courseRecord.bin"
#define REG_FILE "regRecord.bin"

#define Student struct StudentInfo
#define Course struct CourseInfo
#define Reg struct RegInfo

#define max_n (int)100000
#define max_c (int)100
#define max_name 50
#define max_len 10
#define max_p 50
#define fyr 2005

struct CourseInfo {
    int course_year;
    char course_name[max_name];
    char course_semester[max_len];
    int require_amt;
    int cur_amt;
    int max_amt;
    char lecturer[max_name];

    char classCod[max_p];
    char course_code[max_len];
};

struct RegInfo {
    char rID[max_len];
    char rName[max_name];
    int rYear;
    char rSemester[max_len];
    char crsCode[max_len];
    char regCode[max_p];
};

struct StudentInfo {
    char student_id[max_len];
    char student_name[max_name];
    char student_phone[max_p];
    char student_email[max_name];
    char student_major[max_len];
    char password[max_p];

    int student_age;
    int nStdCourse;
    int ord;
};

int N_student, N_course;

void printMessageCenter(const char* message) {
    int len =0;
    int pos = 0;
    len = (78 - strlen(message))/2;
    printf("\t\t\t");
    for(pos = 0 ; pos < len ;++pos) printf(" ");
    printf("%s", message);
}

void Header(const char *message) {
    system("cls");
    printMessageCenter(message);
}

void Note(char *not) {
    printf("\n");
    printMessageCenter(not);
    printf("\n");
}

void Line() {
    printf("\n");
    printMessageCenter("-----------------------------------------------\n");
}

void Load(int t) {
    int wtime = 3;
    while (t) {
        printf("\b \b");
        printf("%d", t);
        sleep(1);
        --t;
    }
}

int isInt(int x) {
    int ox = x;
    int x_ = ++x;
    x_ -= ox;
    if (x_ == 1) return 1;
    else return 0;
}

int isValid(const char *student_name) {
    int validName = 1;
    int len = 0;
    len = strlen(student_name);
    if (len == 0) return 0;
    for(int i=0; i <len ; ++i) {
        if(i<len-1 && (student_name[i] == ' ' && student_name[i+1] == ' ')) {
            validName = 0; break;
        }
        if(!(isalpha(student_name[i])) && (student_name[i] != '\n') && (student_name[i] != ' ')) {
            validName = 0; break;
        }
    }
    return validName;
}

int isValidID(int ID) {
    int cID = ID+1;
    if (cID-ID == 1) return 1;
    return 0;
}

void ReorderSt(FILE * fp) {
    Student curSt;

    rewind(fp);
    if (fp == NULL) return;

    N_student = 0;
    while((fread(&curSt, sizeof(curSt), 1, fp))) {
        if (curSt.ord != 0) ++N_student;
    }
}

void ReorderCrs(FILE * cfp) {
    Course curC;

    rewind(cfp);
    if (cfp == NULL) return;

    N_course = 0;
    while((fread(&curC, sizeof(curC), 1, cfp))) {
        if (curC.course_year != 0) ++N_course;
    }
}

void list_major() {
    printf("\n\t\t\t");
    printf("\n\t\t\t    a. Software Engineering");
    printf("\n\t\t\t    b. Artificial Intelligent");
    printf("\n\t\t\t    c. Information Assurance");
    printf("\n\t\t\t    d. International Business");
    printf("\n\t\t\t    e. Business Administration");
    printf("\n\t\t\t    f. English Language");
}

void Create_student(FILE * fp) {
    char another = 'y';

    fseek(fp, 0, SEEK_END);
    int cont = 1, cur_year;
    char op;
    Header("ADD STUDENTS");
    printf("\n");
    int stt = 0;
    do {
        printf("\n\t\t\tEnrollment year: "); fflush(stdin);
        scanf("%d", &cur_year);

        stt = 1;
        if (!isInt(cur_year)) stt = 0;
        if (cur_year < 2010 || cur_year > 2050) stt = 0;
        if (stt == 0) {
            printf("\n\t\t\tInvalid year!\n\t\t\t");
            if (getch() == 27) return;
            else continue;
        }
    } while (stt == 0);


    do {
        if (cont == 1) {
            Header("ADD STUDENTS");
            char *note = "(press ESC to cancel process after enter an entry completely)";
            Note(note);

            printf("\n\t\t\tEnrollment year: %d", cur_year);

            Student newSt = {0};
            newSt.ord = ++N_student;
            printf("\n\t\t\t(%d)", N_student);

            int status = 0, newID;

            char tmpName[max_name]; status = 0;
            do {
                printf("\n\t\t\tName: "); fflush(stdin);
                fgets(tmpName, max_name, stdin);
                status = isValid(tmpName);
                if (!status) {
                    printf("\n\t\t\tInvalid name. Please enter again.\n");
                }
            } while(!status);
            int it = strlen(tmpName) - 1;
            while (it>=0) { if ('a' <= tmpName[it] && tmpName[it] <= 'z') break; --it; } // remove afterward space
            tmpName[it+1] = '\0';

            //normalize uppercase / lowercase
            tmpName[0] = toupper(tmpName[0]);
            for (int i=1; i<strlen(tmpName); ++i) {
                if (tmpName[i-1] == ' ') tmpName[i] = toupper(tmpName[i]);
                else tmpName[i] = tolower(tmpName[i]);
            }

            strcpy(newSt.student_name, tmpName);

            printf("\n\t\t\tChoose major: "); fflush(stdin);
            list_major();

            int okela = 0;
            do {
                printf("\n\n\t\t\tEnter: "); fflush(stdin);
                char mjo; scanf("%c", &mjo);
                if (mjo == 'a') { strcpy(newSt.student_major, "SE"); okela = 1; break; }
                else if (mjo == 'b') { strcpy(newSt.student_major, "AI"); okela = 1; break; }
                else if (mjo == 'c') { strcpy(newSt.student_major, "IA"); okela = 1; break; }
                else if (mjo == 'd') { strcpy(newSt.student_major, "IB"); okela = 1; break; }
                else if (mjo == 'e') { strcpy(newSt.student_major, "BA"); okela = 1; break; }
                else if (mjo == 'f') { strcpy(newSt.student_major, "EL"); okela = 1; break; }
                else {
                    printf("\n\t\t\tPlease choose a valid index!"); fflush(stdin);
                    okela = 0;
                }
            } while (okela == 0);


            newSt.student_major[strlen(newSt.student_major)] = '\0';
            printf("\n\t\t\tMajor: %s", newSt.student_major); fflush(stdin);

            strcpy(newSt.student_id, newSt.student_major);
            char no[max_len];
            itoa(cur_year - fyr, no, 10);
            strcat(newSt.student_id, no);
            no[0] = '\0';
            itoa(N_student, no, 10);
            int sz = strlen(no); // 2 -> 00**

            char z[max_len];
            for (int i=1; i<=4-sz; ++i) {
                z[0] = '\0'; strcat(z, "0");
                strcat(z, no);
                strcpy(no, z);
            }
            strcat(newSt.student_id, no);
            newSt.student_id[strlen(newSt.student_id)] = '\0';

            printf("\n\t\t\tID: %s", newSt.student_id); fflush(stdin);
            //============= Get mail ================
            {
            char id[max_len];
            itoa(newSt.student_id, id, 10);
            char email[max_name];
            char * sampName = newSt.student_name;

            email[0] = '\0';
            int len = strlen(newSt.student_name);

            int i;
            for (i=len-1; i>=0; --i) {
                if (newSt.student_name[i] == ' ') break;
            }

            int it = 0;
            for (int j=i+1; j<=len-1; ++j) {
                email[it++] = tolower(sampName[j]);
            }
            email[it++] = tolower(sampName[0]);
            for (int j=1; j<i-1; ++j) {
                if ('A' <= sampName[j] && sampName[j] <= 'Z' && sampName[j-1] == ' ') {
                    email[it++] = tolower(sampName[j]);
                }
            }
            email[it] = '\0';

            strcat(email, strlwr(newSt.student_id));
            strcat(email, "@fpt.edu.vn");
            email[strlen(email)] = '\0';

            strcpy(newSt.student_email, email);
            //=======================================
            }
            printf("\n\t\t\tEmail: %s", newSt.student_email); fflush(stdin);

            newSt.nStdCourse = 0;

            strcpy(newSt.password, "123456");
            newSt.password[strlen(newSt.password)] = '\0';
            printf("\n\n\t\t\tENTER to continue / ESC to cancel...");// fflush(stdin); getch();
            fwrite(&newSt, sizeof(newSt), 1, fp);
        }
        printf("\n\t\t\t"); fflush(stdin); op = getch();
		if (op == 27){                                              //CONFIRM CANCEL INPUT PROCESS
            printf("\n\t\t\tConfirm cancel process: Yes (1) / No (2) ");
            int op2; scanf("%d", &op2);
            if (op2 == 2) { cont = 1; }
            else return;
		}
    } while (cont == 1);
}

void outputStd(Student curSt, int flag) {
    if (flag) printf("\n\t\t\t(%d)", strupr(curSt.ord));
    printf("\n\t\t\tID: %s", curSt.student_id);
    printf("\n\t\t\tName: %s", curSt.student_name);
    printf("\n");
    Line();
}

void horizontal_outputStd(Student curSt) {
    printf("%s", strupr(curSt.student_id));
    printf("  | %25s      |  %d course(s) |   %s", curSt.student_name, curSt.nStdCourse, curSt.password);
    printf("\n");
    //Line();
}

void FindName(FILE * fp, int *check) {
    int found = 0;
    Student curSt = {0};

    rewind(fp);

    char sampName[max_name];
    char * cmp;

    printf("\n\n\t\t\tEnter text to find: "); fflush(stdin);
    gets(sampName);

    //Line();
    printf("\n\n\t\t\t        ID     |               Name             |  Registered  |  Password\n");
    while (fread(&curSt, sizeof(curSt), 1, fp)) {
        cmp = strstr(curSt.student_name, sampName);
        if(cmp != NULL) {
            found = 1;
            printf("\n\t\t\t     ");
            horizontal_outputStd(curSt);
        }
    }
    if(found == 0) { printf("\n\t\t\tNO STUDENT WAS FOUND!\n"); *check = 0; }
}

void FindID(FILE * fp, int *check, char studentID[]) {
    int found = 0;
    Student curSt = {0};
    rewind(fp);
    //printf("\n\t\t\tsamp: %s, len: %d", studentID, strlen(studentID));
    //printf("\n\t\t\tsamp: %s, len: %d", studentID, strlen(studentID));

    while (fread(&curSt, sizeof(curSt), 1, fp)) {
        if (!strcmp(strupr(curSt.student_id), strupr(studentID))) {
            horizontal_outputStd(curSt);
            found = 1;
        }
    }
    if(found == 0) {printf("\n\t\t\tNO STUDENT WAS FOUND!\n"); *check = 0; }
}

void Individual(FILE * fp, FILE * rfp, char ID[]) {
    Header("STUDENT'S PERSONAL INFORMATION");
    Student curSt;

    rewind(fp);
    int found = 0;

    while(fread(&curSt, sizeof(curSt), 1, fp)) {
        if (!strcmp(strupr(curSt.student_id), strupr(ID))) {
            found = 1; break;
        }
    }
    if (!found) {
        printf("\n\t\t\tError: ID does not exist!"); fflush(stdin);
        getch(); return;
    }

    printf("\n\n");
    printf("\n\t\t\tID:    %s", curSt.student_id);
    printf("\n\t\t\tName:  %s", curSt.student_name);
    printf("\n\t\t\tMajor: %s", curSt.student_major);
    printf("\n\t\t\tEmail: %s", curSt.student_email);
    printf("\n\t\t\tPhone: %s", curSt.student_phone);
    printf("\n\t\t\tAge:   %d", curSt.student_age);
    //printf("\n\n\t\t\tNumber of registered course: %d", curSt.nStdCourse);
    int stt = 0, yr;
    do {
        Line();
        printf("\n\t\t\tEnter year to view student's registrations!");
        printf("\n\n\t\t\tYear: "); fflush(stdin);
        scanf("%d", &yr);

        stt = 1;
        if (!isInt(yr)) stt = 0;
        if (yr < 2010 || yr > 2050) stt = 0;
        if (stt == 0) {
            printf("\n\t\t\tInvalid year!\n\t\t\t");
            if (getch() == 27) return;
            else continue;
        }
    } while (stt == 0);

    Reg curReg;
    if (curSt.nStdCourse > 0) {
        rewind(rfp);
        int it = 0, found2 = 0;
        printf("\n\n\t\t\t     Course   |   Year   |  Semester\n");
        while((fread(&curReg, sizeof(curReg), 1, rfp))) {
            if (!strcmp(curReg.regCode, "0")) continue;
            if (curReg.rYear == yr) {
                if (!strcmp(strupr(ID), strupr(curReg.rID))) {
                    found2 = 1;
                    printf("\n\t\t\t(%d)", ++it);
                    printf(" %s |   %4d   |   %s\n", curReg.crsCode, curReg.rYear, curReg.rSemester);

                    //strcpy(mp[it], curReg.regCode); // MAP
                }
            }
        }
        //printf("\n\t\t\tFOUND: %d", found2);
        printf("\n\t\t\t");
        if (!found2) printf("\n\n\t\t\tStudent haven't registered any course yet!");
    }
    else {
        printf("\n\n\t\t\tStudent haven't registered any course yet!");
    }
    fflush(stdin);
    getch();
}

void Update_stMajor(FILE * fp, char ID[]) {
    Header("UPDATE MAJOR");

    Student curSt;
    int found=0;
    long long siz = sizeof(curSt);

    rewind(fp);

    while((fread(&curSt, sizeof(curSt), 1, fp))) {
        if(!strcmp(strupr(ID), strupr(curSt.student_id))) {
            found=1; break;
        }
    }

    if(found==1) {
        fseek(fp, -siz, SEEK_CUR);

        printf("\n\n");
        printf("\n\t\t\tCurrent major: %s", curSt.student_major);
        printf("\n\t\t\tChoose new major: ");

        list_major();
        int okela = 0;
        do {
            printf("\n\n\t\t\tEnter: "); fflush(stdin);
            char mjo; scanf("%c", &mjo);
            if (mjo == 'a') { strcpy(curSt.student_major, "SE"); okela = 1; break; }
            else if (mjo == 'b') { strcpy(curSt.student_major, "AI"); okela = 1; break; }
            else if (mjo == 'c') { strcpy(curSt.student_major, "IA"); okela = 1; break; }
            else if (mjo == 'd') { strcpy(curSt.student_major, "IB"); okela = 1; break; }
            else if (mjo == 'e') { strcpy(curSt.student_major, "BA"); okela = 1; break; }
            else if (mjo == 'f') { strcpy(curSt.student_major, "EL"); okela = 1; break; }
            else {
                printf("\n\t\t\tPlease choose a valid index!"); fflush(stdin);
                okela = 0;
            }
        } while (okela == 0);

        curSt.student_major[strlen(curSt.student_major)] = '\0';

        printf("\n\t\t\tNew major: %s", curSt.student_major);

        int len = strlen(curSt.student_id);
        char oldID[max_p];
        strcpy(oldID, curSt.student_id);

        strcpy(curSt.student_id, curSt.student_major);

        for (int i=2; i<len; ++i) {
            strncat(curSt.student_id, &oldID[i], 1);
        }
        curSt.student_id[strlen(curSt.student_id)] = '\0';

        printf("\n\t\t\tID: %s", curSt.student_id); fflush(stdin);
        fwrite(&curSt, sizeof(curSt), 1, fp);
    }
    else printf("\n\n\t\t\t STUDENT CAN'T BE FOUND!");

    printf("\n\n\t\t\tEnter to back...");
    fflush(stdin); getch();
}

void Update_major(FILE * fp, char ID[]) {
    Header("MODIFY STUDENT'S MAJOR");
    Student curSt;

    rewind(fp);
    int found = 0;
    long long siz = sizeof(curSt);

    while(fread(&curSt, sizeof(curSt), 1, fp)) {
        if (!strcmp(strupr(curSt.student_id), strupr(ID))) {
            found = 1; break;
        }
    }
    if (!found) {
        printf("\n\t\t\tError: ID does not exist!"); fflush(stdin);
        getch(); return;
    }
    if(found==1) {
        //fseek(cfp, -siz,SEEK_CUR);
        fseek(fp, -siz, SEEK_CUR);

        printf("\n\n");
        printf("\n\t\t\tCurrent major: %s", curSt.student_major);
        printf("\n\t\t\tChoose new major: ");

        list_major();

        int okela = 0;
        do {
            printf("\n\n\t\t\tEnter: "); fflush(stdin);
            char mjo; scanf("%c", &mjo);
            if (mjo == 'a') { strcpy(curSt.student_major, "SE"); okela = 1; break; }
            else if (mjo == 'b') { strcpy(curSt.student_major, "AI"); okela = 1; break; }
            else if (mjo == 'c') { strcpy(curSt.student_major, "IA"); okela = 1; break; }
            else if (mjo == 'd') { strcpy(curSt.student_major, "IB"); okela = 1; break; }
            else if (mjo == 'e') { strcpy(curSt.student_major, "BA"); okela = 1; break; }
            else if (mjo == 'f') { strcpy(curSt.student_major, "EL"); okela = 1; break; }
            else {
                printf("\n\t\t\tPlease choose a valid index!"); fflush(stdin);
                okela = 0;
            }
        } while (okela == 0);

        curSt.student_major[strlen(curSt.student_major)] = '\0';

        fwrite(&curSt, sizeof(curSt), 1, fp);

        printf("\n\t\t\tMajor: %s", curSt.student_major); fflush(stdin);

        getch();
    }
}

void Find_student(FILE * fp, FILE * rfp) {
    while (1) {
        Header("FIND STUDENT");
        Student curSt;

        rewind(fp);
        if (fp == NULL) {
            printf("\n\t\t\tThere is no student!");
            getch();
            return;
        }

        printf("\n\t\t\t1. Find by name"); fflush(stdin);
        printf("\n\t\t\t2. Find by ID"); fflush(stdin);
        printf("\n\t\t\t0. Back to previous"); fflush(stdin);
        char op[max_len], check = 1;;
        char sampID[max_len];

        printf("\n\n\t\t\tEnter: "); scanf("%s", &op);
        if (strlen(op) > 1) {
            printf("\n\t\t\tInvalid input!");
            fflush(stdin);
            if (getch() == 27) return;
            continue;
        }
        if (op[0] == '0') return;
        else {
            if (op[0] == '1') {                                                  //FIND BY NAME
                Header("FIND STUDENT");
                FindName(fp, &check);
                //printf("\n\t\t\tENTER to continue..."); getch();
            }
            else {
                if (op[0] == '2') {                                              //FIND BY STUDENT ID
                    Header("FIND STUDENT");
                    printf("\n\n\n\t\t\tEnter ID to find: "); fflush(stdin);

                    fgets(sampID, max_len, stdin);
                    sampID[strlen(sampID)-1] = '\0';
                    //Line();
                    printf("\n\n\t\t\t        ID     |               Name             |  Registered  |  Password\n");
                    printf("\n\t\t\t     ");
                    FindID(fp, &check, sampID);
                    //printf("\n\t\t\tENTER to continue..."); getch();
                }
                else {
                    printf("\n\t\t\tInvalid input!"); fflush(stdin);
                    getch();
                    continue;
                }
            }
        }
        if (check == 0) continue;
        //INDIVIDUAL VIEW AND UPDATE
        printf("\n\n\t\t\tChoose what to do next: ");
        printf("\n\n\t\t\t 1. Individual information");
        printf("\n\t\t\t 2. Modify major");
        printf("\n\t\t\t 0. Back");
        printf("\n\n\t\t\tEnter: "); fflush(stdin);
        int op2;  scanf("%d", &op2);
        Line();

        if (op2 == 0) continue;

        if (op2 == 1) {                                              //INDIVIDUAL VIEW
            if (strlen(sampID) == 0) {
                printf("\n\t\t\tStudent ID: "); fflush(stdin);
                //scanf("%d", &sampID);
                fgets(sampID, max_len, stdin);
                sampID[strlen(sampID)-1] = '\0';
            }
            Individual(fp, rfp, sampID);
        }
        if (op2 == 2) {                                              //UPDATE MAJOR
            if (strlen(sampID) == 0) {
                printf("\t\t\tStudent ID: "); fflush(stdin);
                //scanf("%d", &sampID);
                fgets(sampID, max_len, stdin);
                sampID[strlen(sampID)-1] = '\0';
            }
            rewind(fp);
            Update_stMajor(fp, sampID);
        }
    }
}

void del(FILE * fp, int cur_ord) {
    Student curSt;

    rewind(fp);

    int found=0;
    long long siz = sizeof(curSt);

    rewind(fp);

    while((fread(&curSt, sizeof(curSt), 1, fp))) {
        if (curSt.ord != 0 && curSt.ord == cur_ord) {
            found=1; break;
        }
    }

    if(found == 1) {
        fseek(fp, -siz, SEEK_CUR);
        curSt.ord = 0;
        fwrite(&curSt, sizeof(curSt), 1, fp);
    }
    else return;
}

void Delete_student(FILE * fp, int mp[]) {
    printf("\n\t\t\tInput separate by SPACE BAR button, ENTER to complete.");
    printf("\n\t\t\tEnter order of student you want to delete:\n");
    printf("\n\t\t\t"); fflush(stdin);

    char ch;
    int delOrd;

    do {
        if (ch == 10) break;
        scanf("%d", &delOrd);
        del(fp, mp[delOrd]);
        ch = getchar();
    } while (ch != 10);

    printf("\n\t\t\tDeleted successfully!"); fflush(stdin);
    getch();

}

void Student_list(FILE * fp) {
     while (1) {
        Header("STUDENT LIST");

        Student curSt;
        int countStudent = 0;
        int cnt[6], mp[max_n];
        for (int i=0; i<7; ++i) cnt[i] = 0;
        for (int i=0; i<max_n; ++i) mp[i] = 0;

        rewind(fp);
        if (fp == NULL) {
            printf("\n\t\t\tThere is no student!");
            getch();
            return;
        }
        printf("\n\n\t\t\t        ID     |               Name             |  Registered  |  Password\n");
        while((fread(&curSt, sizeof(curSt), 1, fp))) {
            if (curSt.ord != 0) {
                printf("\n\t\t\t(%d)  ", ++countStudent);
                horizontal_outputStd(curSt);
                mp[countStudent] = curSt.ord;
                {
                if (!strcmp(toupper(curSt.student_major), "SE")) ++cnt[0];
                if (!strcmp(toupper(curSt.student_major), "AI")) ++cnt[1];
                if (!strcmp(toupper(curSt.student_major), "IA")) ++cnt[2];
                if (!strcmp(toupper(curSt.student_major), "IB")) ++cnt[3];
                if (!strcmp(toupper(curSt.student_major), "BA")) ++cnt[4];
                if (!strcmp(toupper(curSt.student_major), "EL")) ++cnt[5]; }
            }
        }

        if(!countStudent) {
            printf("\n\t\t\tThere is no student!");
            printf("\n\n\t\t\tENTER to back to main menu..."); fflush(stdin);
            getch(); return;
        }
        else {
            Line();
            printf("\n\t\t\tStatistic:\n"); {
            printf("\n\t\t\tSE: %d student", cnt[0]); if (cnt[0] > 1) printf("s");
            printf("\n\t\t\tAI: %d student", cnt[1]); if (cnt[1] > 1) printf("s");
            printf("\n\t\t\tIA: %d student", cnt[2]); if (cnt[2] > 1) printf("s");
            printf("\n\t\t\tIB: %d student", cnt[3]); if (cnt[3] > 1) printf("s");
            printf("\n\t\t\tBA: %d student", cnt[4]); if (cnt[4] > 1) printf("s");
            printf("\n\t\t\tEL: %d student", cnt[5]); if (cnt[5] > 1) printf("s"); }
        }
        printf("\n\n\t\t\t1. Delete students");
        printf("\n\t\t\t0. Back");
        printf("\n\n\t\t\tEnter: ");
        fflush(stdin);
        char ch; scanf("%c", &ch);
        if (ch == '0') return;
        else if (ch == '1') Delete_student(fp, mp);
        else continue;
    }

}

void Student_list_viaMaj(FILE * fp, char sampMaj[]) {
     while (1) {
        Header("STUDENT LIST");

        Student curSt;
        int countStudent = 0;
        int mp[max_n];
        for (int i=0; i<max_n; ++i) mp[i] = 0;

        rewind(fp);
        if (fp == NULL) {
            printf("\n\t\t\tThere is no student!");
            getch();
            return;
        }

        printf("\n\n\t\t\t        ID     |               Name             |  Registered  |  Password\n");
        while((fread(&curSt, sizeof(curSt), 1, fp))) {
            if (curSt.ord == 0) continue;
            if (!strcmp(strupr(sampMaj), strupr(curSt.student_major))) {
                printf("\n\t\t\t(%d)  ", ++countStudent);
                horizontal_outputStd(curSt);
                mp[countStudent] = curSt.ord;

            }
        }

        if(!countStudent) {
            printf("\n\t\t\tThere is no student!");
            printf("\n\n\t\t\tENTER to back to main menu..."); fflush(stdin);
            getch(); return;
        }

        printf("\n\n\t\t\t1. Delete students");
        printf("\n\t\t\t0. Back");
        printf("\n\n\t\t\tEnter: ");
        fflush(stdin);
        char ch; scanf("%c", &ch);
        if (ch == '0') return;
        else if (ch == '1') Delete_student(fp, mp);
        else continue;
    }

}

void Display_student(FILE * fp) {
    while (1) {
        Header("STUDENT LIST");

        Note("You will need to enter major to view student list of input major");
        printf("\n\n\t\t\t1. Single major");
        printf("\n\t\t\t2. Entire list");
        printf("\n\t\t\t0. Back");

        char op[max_len];
        printf("\n\n\t\t\tEnter: "); fflush(stdin);
        gets(op);
        if (strlen(op) > 1) {
            printf("\n\t\t\tInvalid input!"); fflush(stdin);
            if (getch() == 27) return;
            continue;
        }

        switch(op[0]) {
            case '1':
                printf("\n\t\t\tEnter major: "); fflush(stdin);
                char cur_maj[max_len];
                scanf("%s", &cur_maj);

                Student_list_viaMaj(fp, cur_maj);
                break;
            case '2':
                Student_list(fp);
                break;
            case '0':
                return;
            default:
                printf("\n\t\t\t\t\tINVALID INPUT!!! Try again...");
                fflush(stdin); getch();
        }


    }

}

int traserve(FILE * cfp, char code[]) {
    rewind(cfp);
    int cnt = 0;
    Course curC;
    while (fread(&curC, sizeof(curC), 1, cfp)) {
        if (!strcmp(strupr(curC.course_code), strupr(code)))
            ++cnt;
    }
    return cnt;
}

void Create_course(FILE * cfp) {
    Header("ADD COURSE");
    char *note = "(press ESC to cancel process after enter an entry completely)";
    Note(note);

    char another = 'y';
    Course curC;

    rewind(cfp);
    if (cfp == NULL) return;

    N_course = 0;
    while((fread(&curC, sizeof(curC), 1, cfp))) {
        if (curC.course_year != 0) ++N_course;
    }
    fseek(cfp, 0, SEEK_END);

    int cont = 1;
    char op;

    int year;
    int stt = 0;
    do {
        printf("\n\t\t\tYear: "); fflush(stdin);
        scanf("%d", &year);

        stt = 1;
        if (!isInt(year)) stt = 0;
        if (year < 2010 || year > 2050) stt = 0;
        if (stt == 0) {
            printf("\n\t\t\tInvalid year!\n\t\t\t");
            if (getch() == 27) return;
            else continue;
        }
    } while (stt == 0);

    printf("\t\t\tSemester: "); fflush(stdin);
    printf("\n\t\t\t");
    printf("\n\t\t\t    a. spring");
    printf("\n\t\t\t    b. summer");
    printf("\n\t\t\t    c. fall");

    stt = 0;
    char * sampSem;
    do {
        printf("\n\n\t\t\tEnter: "); fflush(stdin);
        char semOp; scanf("%c", &semOp);

        stt = 1;
        if (semOp == 'a') strcpy(sampSem, "spring");
        else if (semOp == 'b') strcpy(sampSem, "summer");
        else if (semOp == 'c') strcpy(sampSem, "fall");
        else {
            printf("\n\t\t\tInvalid input!"); fflush(stdin);
            if (getch() == 27) return;
            stt = 0;
            continue;
        }

    } while (stt == 0);
    sampSem[strlen(sampSem)] = '\0';
    strlwr(sampSem);

    //N_course = 0;
    char sampCode[max_len];
    do {
        if (cont == 1) {
            Header("ADD COURSE");
            char *note = "(press ESC to cancel process after enter an entry completely)";
            Note(note);

            printf("\n\n\t\t\tYear: %d", year);
            printf("\n\t\t\tSemester: %s", sampSem);
            Course newC;

            ++N_course;
            printf("\n\t\t\t(%d)", N_course);

            newC.course_year = year;
            strcpy(newC.course_semester, sampSem);

            printf("\n\t\t\tCourse name: "); fflush(stdin);
            fgets(newC.course_name, max_name, stdin);
            newC.course_name[strlen(newC.course_name)-1] = '\0';
            strlwr(newC.course_name);

            //======= GEN CODE ========

            sampCode[0] = '\0';
            int ind = 0;
            strncat(sampCode, &newC.course_name[0], 1);
            if (newC.course_name[++ind] != ' ')
                strncat(sampCode, &newC.course_name[1], 1);

            for (int i=2; i<strlen(newC.course_name); ++i) {
                if (newC.course_name[i-1] == ' ')
                    strncat(sampCode, &newC.course_name[i], 1);
            }

            int yr_part = year%100;
            char str_yr[10];
            itoa(yr_part, str_yr, 10);
            strcat(sampCode, str_yr);

            if (!strcmp(sampSem, "spring")) strcat(sampCode, "1");
            if (!strcmp(sampSem, "summer")) strcat(sampCode, "2");
            if (!strcmp(sampSem, "fall")) strcat(sampCode, "3");

            sampCode[strlen(sampCode)] = '\0';
            strupr(sampCode);

            strcpy(newC.course_code, sampCode);
            printf("\t\t\tCourse code: %s\n", newC.course_code); fflush(stdin);

            //=========================

            // newC.classCod = course_code + [no]
            int no = traserve(cfp, sampCode); ++no;
            char NoStr[max_len];

            itoa(no, NoStr, 10);
            char Z[max_len] = "0";
            if (no < 10) strcat(Z, NoStr);
            strcpy(newC.classCod, newC.course_code);
            strcat(newC.classCod, "_");
            strcat(newC.classCod, Z);

            //if (no < 10) strcpy(NoStr, strcat("0", NoStr));
            printf("\t\t\tClass: %s\n", newC.classCod);

            //=========================

            printf("\t\t\tRequire number: "); fflush(stdin);
            scanf("%d", &newC.require_amt);

            int maxx;
            stt = 0;
            do {
                stt = 1;
                printf("\t\t\tMaximum: "); fflush(stdin);
                scanf("%d", &maxx);
                if (maxx < newC.require_amt) {
                    printf("\n\t\t\tMaximum number must be greater than require number!");
                    fflush(stdin); getch();
                    printf("\n\n");
                    stt = 0;
                    continue;
                }
            } while (stt == 0);
            newC.max_amt = maxx;


            //======= LECTURER NAME ========
            char sampName[max_name]; int status = 0;
            do {
                printf("\t\t\tLecturer: "); fflush(stdin);
                fgets(sampName, max_name, stdin);
                status = isValid(sampName);
                if (!status) {
                    printf("\n\t\t\tInvalid name. Please enter again.\n");
                }
            } while(!status);
            int it = strlen(sampName) - 1;
            while (it>=0) {
                if ('a' <= sampName[it] && sampName[it] <= 'z') break;
                --it;
            } //remove afterward space
            sampName[it+1] = '\0';

            //normalize uppercase / lowercase
            sampName[0] = toupper(sampName[0]);
            it = strlen(sampName) - 1;
            while (it>=1) {
                if (sampName[it-1] == ' ') sampName[it] = toupper(sampName[it]);
                else sampName[it] = tolower(sampName[it]); ;
                --it;
            }
            //printf("\n\t\t\t %s", sampName);

            //============= NORMALIZE LECTURER NAME ================
            {
            char normalized_name[max_name];
            normalized_name[0] = '\0';
            int len = strlen(sampName);

            int i;
            for (i=len-1; i>=0; --i) {
                if (sampName[i] == ' ') break;
            }

            int it = 0;
            normalized_name[it++] = sampName[i+1];
            for (int j=i+2; j<=len-1; ++j) {
                normalized_name[it++] = tolower(sampName[j]);
            }
            normalized_name[it++] = sampName[0];
            for (int j=1; j<i-1; ++j) {
                if ('A' <= sampName[j] && sampName[j] <= 'Z' && sampName[j-1] == ' ') {
                    normalized_name[it++] = sampName[j];
                }
            }
            normalized_name[it] = '\0';

            strcpy(newC.lecturer, normalized_name);
            //printf("\n\t\t\t%s", newC.lecturer);
            //======================================================
            }


            newC.cur_amt = 0;
            fwrite(&newC, sizeof(newC), 1, cfp);

            printf("\n\n\t\t\tENTER to continue / ESC to cancel...");// fflush(stdin); getch();

        }
        printf("\n\t\t\t"); fflush(stdin); op = getch();
		if (op == 27){                                              //CONFIRM CANCEL INPUT PROCESS
            printf("\n\t\t\tConfirm cancel process: Yes (1) / No (2) ");
            int op2; scanf("%d", &op2);
            if (op2 == 2) { cont = 1; }
            else return;
		}
    } while (cont == 1);
}

void horizontal_outputCrs(Course curC) {
    char * stt, * stt2;
    if (curC.cur_amt >= curC.require_amt) stt = "YES";
    else stt = "NO";
    if (curC.cur_amt == curC.max_amt) stt2 = "FULL SLOT";
    else stt2 = "NOT FULL";

    printf(" %6s  |  %8s  |     %03d     |    %03d    |  %5s     |  %5s", strupr(curC.classCod), curC.lecturer, curC.cur_amt, curC.max_amt, stt, stt2);
    printf("\n");
    //Line();
}

void Update_lecturer(FILE * cfp, char clsCode[]) {
    Header("UPDATE LECTURER");

    Course curC;
    int found=0;
    long long siz = sizeof(curC);

    rewind(cfp);
    while((fread(&curC, sizeof(curC), 1, cfp))) {
        if(curC.course_year != 0 && !strcmp(strupr(clsCode), strupr(curC.classCod))) {
            found=1; break;
        }
    }

    if(found==1) {
        fseek(cfp, -siz,SEEK_CUR);

        printf("\n\n\t\t\t");
        horizontal_outputCrs(curC);

        char sampName[max_name]; int status = 0;
        do {
            printf("\n\n\t\t\t New lecturer's name: "); fflush(stdin);
            fgets(sampName, max_name, stdin);
            status = isValid(sampName);
            if (!status) {
                printf("\n\t\t\tInvalid name. Please enter again.\n");
            }
        } while(!status);
        int it = strlen(sampName) - 1;
        while (it>=0) {
            if ('a' <= sampName[it] && sampName[it] <= 'z') break;
            --it;
        }
        sampName[it+1] = '\0';

        //============= NORMALIZE LECTURER NAME ================
        {
        char normalized_name[max_name];
        normalized_name[0] = '\0';
        int len = strlen(sampName);

        int i;
        for (i=len-1; i>=0; --i) {
            if (sampName[i] == ' ') break;
        }

        int it = 0;
        normalized_name[it++] = sampName[i+1];
        for (int j=i+2; j<=len-1; ++j) {
            normalized_name[it++] = tolower(sampName[j]);
        }
        normalized_name[it++] = sampName[0];
        for (int j=1; j<i-1; ++j) {
            if ('A' <= sampName[j] && sampName[j] <= 'Z' && sampName[j-1] == ' ') {
                normalized_name[it++] = sampName[j];
            }
        }
        normalized_name[it] = '\0';

        strcpy(curC.lecturer, normalized_name);
        //printf("\n\t\t\t%s", newC.lecturer);
        //======================================================
        }


        printf("\n\n\t\t\t UPDATE SUCCESSFULLY!");

        printf("\n\n\t\t\t");
        horizontal_outputCrs(curC);

        fwrite(&curC, sizeof(curC), 1, cfp);
    }
    else printf("\n\n\t\t\t COURSE CAN'T BE FOUND!");

    printf("\n\n\t\t\t Press Enter to back...");
    fflush(stdin); getch();
}

void Update_slot(FILE * cfp, char clsCode[]) {
    Header("UPDATE SLOT");

    Course curC;
    int found=0;
    long long siz = sizeof(curC);

    rewind(cfp);

    while((fread(&curC, sizeof(curC), 1, cfp))) {
        if(curC.course_year != 0 && !strcmp(strlwr(clsCode), strlwr(curC.classCod))) {
            found=1; break;
        }
    }

    if(found==1) {
        fseek(cfp, -siz,SEEK_CUR);
          printf("\n\n\t\t\t");

        printf("\n\t\t\t       Code    |  Lecturer  |  St.Number  |  Maximum  |   Require number\n");
        printf("\n\t\t\t");
        printf("    %6s  |  %8s  |     %03d     |    %03d    |        %03d", strupr(curC.classCod), curC.lecturer, curC.cur_amt, curC.max_amt, curC.require_amt);
        printf("\n");
        //Line();

        int new_max, require_amt;
        printf("\n\n\t\t\t New maximum slot: "); fflush(stdin); scanf("%d", &new_max);
        printf("\t\t\t New require amount: "); fflush(stdin); scanf("%d", &require_amt);

        curC.max_amt = new_max;
        curC.require_amt = require_amt;

        printf("\n\n\t\t\t UPDATE SUCCESSFULLY!\n");
        printf("\n\t\t\t");
        printf("    %6s  |  %8s  |     %03d     |    %03d    |        %03d", curC.course_code, curC.lecturer, curC.cur_amt, curC.max_amt, curC.require_amt);
        fwrite(&curC, sizeof(curC), 1, cfp);
    }
    else printf("\n\n\t\t\t COURSE CAN'T BE FOUND!");

    printf("\n\n\t\t\t Press Enter to back...");
    fflush(stdin); getch();
}

void Rename_course(FILE * cfp, char crsID[]) {
    Header("RENAME COURSE");

    Course curC;
    int found=0;
    long long siz = sizeof(curC);

    rewind(cfp);

    while((fread(&curC, sizeof(curC), 1, cfp))) {
        if(curC.course_year != 0 && !strcmp(strupr(crsID), strupr(curC.course_code))) {
            found=1; break;
        }
    }

    if(found==1) {
        fseek(cfp, -siz, SEEK_CUR);
        printf("\n\n\t\t\t FOUND!\n");
        printf("\n\t\t\t  Code   |  Lecturer  |  St.Number  |  Satisfy?  |   Status");
        printf("\n\t\t\t");
        horizontal_outputCrs(curC);

        char new_crs_name[max_name];
        printf("\n\n\t\t\tNew course name: "); fflush(stdin);
        fgets(new_crs_name, max_name, stdin);
        new_crs_name[strlen(new_crs_name)-1] = '\0';
        strcpy(curC.course_name, strlwr(new_crs_name));
        strlwr(curC.course_name);
        //======= GEN CODE ========

        char sampCode[max_len];
        int ind = 0;
        sampCode[ind] = curC.course_name[ind];
        if (curC.course_name[++ind] != ' ') {
            sampCode[ind] = curC.course_name[ind];
            sampCode[++ind] = '\0';
        }
        //printf("\n\t\t\t%s", sampCode);
        for (int i=2; i<strlen(curC.course_name); ++i) {

            if (curC.course_name[i-1] == ' ') {
                strncat(sampCode, &curC.course_name[i], 1);
            }
        }

        int yr_part = curC.course_year%100;
        char str_yr[10];
        itoa(yr_part, str_yr, 10);
        strcat(sampCode, str_yr);

        if (!strcmp(curC.course_semester, "spring")) strcat(sampCode, "1");
        if (!strcmp(curC.course_semester, "summer")) strcat(sampCode, "2");
        if (!strcmp(curC.course_semester, "fall")) strcat(sampCode, "3");

        sampCode[strlen(sampCode)] = '\0';
        strupr(sampCode);

        strcpy(curC.course_code, sampCode);
        printf("\n\t\t\tCourse code: %s\n", curC.course_code); fflush(stdin);

        //=========================



        printf("\n\n\t\t\t UPDATE SUCCESSFULLY!");

        printf("\n\n\t\t\t");
        horizontal_outputCrs(curC);

        fwrite(&curC, sizeof(curC), 1, cfp);
    }
    else printf("\n\n\t\t\t COURSE CAN'T BE FOUND!");

    printf("\n\n\t\t\t Press Enter to back...");
    //fflush(stdin);
    //getch();
}

void Delete_course(FILE * cfp, char crsID[]) {
    Course curC;
    int found=0;
    long long siz = sizeof(curC);

    rewind(cfp);

    while((fread(&curC, sizeof(curC), 1, cfp))) {
        if(!strcmp(strupr(crsID), strupr(curC.course_code))) {
            found=1; break;
        }
    }

    if(found == 1) {
        fseek(cfp, -siz, SEEK_CUR);
        curC.course_year = 0;
        fwrite(&curC, sizeof(curC), 1, cfp);

        printf("\n\n\t\t\t\tCourse was deleted successfully!...");

        fflush(stdin);
       // getch();
        return;
    }
}

void Class_student(FILE * fp, FILE * rfp, char clsCode[]) {
    Header("STUDENT LIST OF CLASS");

    Reg curR;
    int found=0;

    rewind(rfp);

    int nSt = 0;
    printf("\n\n");
    while((fread(&curR, sizeof(curR), 1, rfp))) {
        if(!strcmp(curR.regCode, "0")) continue;
        if(!strcmp(strlwr(clsCode), strlwr(curR.crsCode))) {
            printf("\n\t\t\t(%d) ", ++nSt);
            printf("%6s  |   %s", curR.rID, curR.rName);
        }
    }

    printf("\n\n\t\t\tEnter to back...");
    fflush(stdin); getch();
}

void View_class(FILE * fp, FILE * cfp, FILE * rfp, char crsID[]) {
    while (1) {
        Header("VIEW CLASSES OF A COURSE");

        Course curC;
        int found=0;
        long long siz = sizeof(curC);

        rewind(cfp);

        int cnt = 0;
        printf("\n\n\n\t\t\t      Code     |  Lecturer  |  St.Number  |  Maximum  |  Satisfy?  |   Status\n");
        while((fread(&curC, sizeof(curC), 1, cfp))) {
            if(curC.course_year != 0 && !strcmp(strupr(crsID), strupr(curC.course_code))) {
                printf("\n\t\t\t(%d)", ++cnt);
                horizontal_outputCrs(curC);
            }
        }
        if (cnt == 0) {
            printf("\n\t\t\tError: wrong class code or invalid input!");
            fflush(stdin); getch(); return;
        }
        printf("\n\n\t\t\t 1. Update lecturer");
        printf("\n\t\t\t 2. Update maximum slot");
        printf("\n\t\t\t 3. Student list of a class");
        printf("\n\t\t\t 0. Back");
        fflush(stdin);
        int stt = 0;
        char option[100];
        do {
            stt = 1;
            printf("\n\n\t\t\t Enter: "); fflush(stdin);
            scanf("%s", &option);
            if (strlen(option) > 1) {
                printf("\n\t\t\tINVALID INPUT! Try again...");
                stt = 0;
                continue;
            }
        } while (stt == 0);

        if (!strcmp(option, "0")) return;
        char clsCode[max_p];

        switch(option[0]) {
            case '1':
                printf("\n\t\t\t Class code: "); fflush(stdin);
                scanf("%s", &clsCode);
                Update_lecturer(cfp, clsCode); break;
            case '2':
                printf("\n\t\t\t Class code: "); fflush(stdin);
                scanf("%s", &clsCode);
                Update_slot(cfp, clsCode); break;
            case '3':
                printf("\n\t\t\t Class code: "); fflush(stdin);
                scanf("%s", &clsCode);
                Class_student(fp, rfp, clsCode); break;
            case '0':
                return;
            default:
                printf("\n\t\t\tINVALID INPUT!!! Try again...\n");
                fflush(stdin); getch();
        }
    }
 }

void Course_list(FILE * fp, FILE * cfp, FILE * rfp) {
    Header("COURSE LIST");

    rewind(cfp);
    if (cfp == NULL) {
        printf("\n\t\t\tThere is no course!");
        getch();
        return;
    }

    int yr; char sem[max_len];
    int stt = 0;
    do {
        printf("\n\t\t\tYear: "); fflush(stdin);
        scanf("%d", &yr);

        stt = 1;
        if (!isInt(yr)) stt = 0;
        if (yr < 2010 || yr > 2050) stt = 0;
        if (stt == 0) {
            printf("\n\t\t\tInvalid year!\n\t\t\t");
            if (getch() == 27) return;
            else continue;
        }
    } while (stt == 0);
    printf("\t\t\tSemester: "); fflush(stdin);
    printf("\n\t\t\t");
    printf("\n\t\t\t    a. spring");
    printf("\n\t\t\t    b. summer");
    printf("\n\t\t\t    c. fall");
    stt = 0;
    do {
         printf("\n\n\t\t\tEnter: "); fflush(stdin);
        char semOp; scanf("%c", &semOp);

        stt = 1;
        if (semOp == 'a') strcpy(sem, "spring");
        else if (semOp == 'b') strcpy(sem, "summer");
        else if (semOp == 'c') strcpy(sem, "fall");
        else {
            printf("\n\t\t\tInvalid input!"); fflush(stdin);
            if (getch() == 27) return;
            stt = 0;
            continue;
        }

    } while (stt == 0);
    sem[strlen(sem)] = '\0';
    strlwr(sem);

    char crsID[max_len];
    Course curC;
    long long siz = sizeof(curC);

    while (1) {
        Header("COURSE LIST");

        printf("\n\t\t\tYear: %d", yr);
        printf("\n\t\t\tSemester: %s\n", sem);

        int cntCourse = 0;

        rewind(cfp);
        printf("\n\t\t\t    Course  |  Number of class");
        printf("\n\t\t\t----------------------------------\n");
        char List[max_c][max_len], it = 0;
        while((fread(&curC, sizeof(curC), 1, cfp))) {
            if (curC.course_year == yr && !strcmp(strlwr(curC.course_semester), strlwr(sem))) {
                //printf("\n\t\t\t(%d)", ++cntCourse);
                //horizontal_outputCrs(curC);

                //int no = traserve(cfp, curC.course_code);

                //printf(" %6s  |  %12s  ", strupr(curC.course_code), curC.course_name);
                strcpy(List[++it], strupr(curC.course_code));
            }
        }
        if (it == 0) {
            printf("\n\t\t\tThere is no course!\n\t\t\t"); fflush(stdin);
            if (getch() == 27) return;
        }

        //==== SORT =====
        char temp[max_len];
        for (int i=1; i<it; ++i) {
            for (int j=i+1; j<=it; ++j) {
                if (strcmp(List[i], List[j])) {
                    strcpy(temp, List[i]);
                    strcpy(List[i],List[j]);
                    strcpy(List[j],temp);
                }
            }
        }
        //for (int i=1; i<=it; ++i) { printf("\n\t\t\t    %6s", List[i]); }
        //printf("\n\n");
        int cntt = 1;
        for (int i=1; i<=it; ++i) {
            if (!strcmp(List[i], List[i+1])) {
                ++cntt;
            }
            else {
                printf("\n\t\t\t    %6s  |      %03d\n", List[i], cntt);
                cntt = 1;
            }
        }
        printf("\n\t\t\t----------------------------------\n");
        //======end list==========

        printf("\n\t\t\t 1. View classes");
        //printf("\n\t\t\t 2. Delete course");

        printf("\n\t\t\t 0. Back");

        printf("\n\n\t\t\t Enter: "); fflush(stdin);
        int option; scanf("%d", &option);

        //if (option == 0) return;
        //if (option != 1 && option != 2) continue;
        if (!isInt(option)) {
            printf("\n\t\t\tINVALID INPUT!!! Try again...");
            fflush(stdin); getch();
            continue;
        }
        switch(option) {
            case 1:
                printf("\n\t\t\t Code of the course: "); fflush(stdin);
                scanf("%s", &crsID);
                View_class(fp, cfp, rfp, crsID); break;
            /*case 2:
                printf("\n\t\t\t Code of the course: "); fflush(stdin);
                scanf("%s", &crsID);
                Delete_course(cfp, crsID); break;*/
            case 0:
                return;
            default:
                printf("\n\t\t\t INVALID INPUT!!! Try again...");
                fflush(stdin);// getch();
        }
        getch();
    }
}

void staff_department(FILE * fp, FILE * cfp, FILE * rfp, int* endp) {
    while(1) {
        Header("STAFF MENU");

        printf("\n\n\n\t\t\t\t 1. Create students");
        printf("\n\t\t\t\t 2. Find a student");
        printf("\n\t\t\t\t 3. Delete student");
        printf("\n\t\t\t\t 4. Create courses");
        printf("\n\t\t\t\t 5. Course list");
        printf("\n\t\t\t\t 0. Back\n\t");

        printf("\n\n\t\t\t\tEnter: "); fflush(stdin);
        char oop[1000];
        gets(oop);
        if (strlen(oop) > 1) {
            printf("\n\t\t\t\t\tINVALID INPUT!!! Try again...");
            fflush(stdin); getch();
            continue;
        }

        *endp = 0;
        switch(oop[0]) {
            case '1':
                ReorderSt(fp);
                Create_student(fp); break;
            case '2':
                Find_student(fp, rfp); break;
            case '3':
                ReorderSt(fp);
                Display_student(fp); break;
            case '4':
                //ReorderCrs(cfp);
                Create_course(cfp); break;
            case '5':
                Course_list(fp, cfp, rfp); break;
            case '0':
                system("cls");
                *endp = 1;
                return;
                break;
            default:
                printf("\n\t\t\t\t\tINVALID INPUT!!! Try again...");
                fflush(stdin); getch();
        }
    }
}

void slogin(int *check) {
    Header("LOGIN TO STAFF MENU");
    printf("\n\n");
    Line();
    Note("You need to enter password to log in");
    Note("(Valid password us not longer than 12 characters and does not contain special symbol.)");

    printf("\n\n\t\t\tPassword: "); fflush(stdin);

    char ch, password[max_p];
    int it=0;

    do {
        fflush(stdin); ch=getch();
        if (ch == 13) {//ENTER
            password[it] = '\0'; break;
        }
        else if (ch == 8) {//backspace
            if (it > 0) { printf("\b \b"); --it; }
            continue;
        }
        else if (ch == 9 || ch == 32) continue; // tab | space
        password[it] = ch;
        ++it;
        printf("*");
    } while(ch != '\r');

    if (strcmp(password, "fptustaff101")) {
        printf("\n\n\n");
        printMessageCenter("WRONG PASSWORD!");
        printf("\n\n\n");
        *check = 0;
        printf("\n\t\t\tENTER to try again / ESC to back to previous...");
        return;
    }
    else {
        printf("\n\n\n");
        printMessageCenter("CORRECT PASSWORD");
        printf("\n\n\n");
        *check = 1;
        return;
    }
}

void LoginStaff(FILE * fp, FILE * cfp, FILE * rfp) {
    int endp;
    while (1) {
        int check = 0;
        char *ID;
        slogin(&check);
        if (!check) {
            if (getch() == 27) return;
            else continue;
        }

        printf("\n\t\t\tGETTING ACCESS TO STAFF MENU...");
        //printf("\n\t\t\tID: %s", &ID);
        Load(3);
        endp = 0;
        staff_department(fp, cfp, rfp, &endp);
        if (endp == 1) return;
    }
}

void Student_info(FILE * fp, char *ID) {
    while (1) {
        Header("ADD PERSONAL INFORMATION");

        Student curSt;
        int found=0;
        long long siz = sizeof(curSt);

        rewind(fp);

        while((fread(&curSt, sizeof(curSt), 1, fp))) {
            if (curSt.ord != 0 && !strcmp(strupr(ID), strupr(curSt.student_id))) {
                found=1; break;
            }
        }

        if(found==1) {
            fseek(fp, -siz,SEEK_CUR);
            printf("\n\t\t\tID: %s", curSt.student_id);
            printf("\n\t\t\tName: %s", curSt.student_name);
            printf("\n\t\t\tMajor: %s", strupr(curSt.student_major));
            printf("\n\t\t\tEmail: %s", curSt.student_email);

            int stt = 0;
            do {
                printf("\n\t\t\tPhone: "); fflush(stdin);
                fgets(curSt.student_phone, max_p, stdin);
                curSt.student_phone[strlen(curSt.student_phone)-1] = '\0';
                stt = 1;
                if (strlen(curSt.student_phone) != 10) stt = 0;
                if (curSt.student_phone[0] != '0') stt = 0;
                if (!stt) {
                    printf("\n\t\t\tInvalid phone number!\n\t\t\t"); fflush(stdin);
                    if (getch() == 27) return;
                    continue;
                }
            } while (stt == 0);

            stt = 0;
            do {
                printf("\t\t\tAge:   "); fflush(stdin);
                scanf("%d", &curSt.student_age);
                stt = 1;
                if (!isInt(curSt.student_age)) stt = 0;
                if (curSt.student_age < 0 || curSt.student_age > 99) stt = 0;
                if (!stt) {
                    printf("\n\t\t\tInvalid age!\n\t\t\t"); fflush(stdin);
                    if (getch() == 27) return;
                    printf("\n");
                    continue;
                }

            } while (stt == 0);


            fwrite(&curSt, sizeof(curSt), 1, fp);
        }
        else printf("\n\n\t\t\tSTUDENT CAN'T BE FOUND!");

        printf("\n\n\t\t\tPress ESC to back...");
        fflush(stdin); if (getch() == 27) return;

    }
}

int cntReg(FILE * fp, FILE * rfp, char ID[]) {
    rewind(fp);
    rewind(rfp);

    Reg curR;
    int cnt = 0;
    while (fread(&curR, sizeof(curR), 1, rfp)) {
        ++cnt;
    }
    return cnt;
}

int alreadyInClass(FILE * rfp, char crs[], char ID[]) {
    rewind(rfp);
    int check = 1;
    Reg curR;

    while (fread(&curR, sizeof(curR), 1, rfp)) {
        if (!strcmp(strupr(ID), strupr(curR.rID))) {
            if (!strcmp(strupr(crs), strupr(curR.crsCode))) check = 0;
        }
    }
    return check;
}

void Registration(FILE * fp, FILE * cfp, FILE * rfp, char ID[]) {
    Header("REGISTER COURSE");
    int yr; char sem[max_len]; sem[0] = '\0';

    int stt = 0;
    do {
        printf("\n\t\t\tYear: "); fflush(stdin);
        scanf("%d", &yr);

        stt = 1;
        if (!isInt(yr)) stt = 0;
        if (yr < 2010 || yr > 2050) stt = 0;
        if (stt == 0) {
            printf("\n\t\t\tInvalid year!\n\t\t\t");
            if (getch() == 27) return;
            else continue;
        }
    } while (stt == 0);
    printf("\t\t\tSemester: "); fflush(stdin);
    printf("\n\t\t\t");
    printf("\n\t\t\t    a. spring");
    printf("\n\t\t\t    b. summer");
    printf("\n\t\t\t    c. fall");
    stt = 0;
    do {
         printf("\n\n\t\t\tEnter: "); fflush(stdin);
        char semOp; scanf("%c", &semOp);

        stt = 1;
        if (semOp == 'a') strcpy(sem, "spring");
        else if (semOp == 'b') strcpy(sem, "summer");
        else if (semOp == 'c') strcpy(sem, "fall");
        else {
            printf("\n\t\t\tInvalid input!"); fflush(stdin);
            if (getch() == 27) return;
            stt = 0;
            continue;
        }

    } while (stt == 0);
    sem[strlen(sem)] = '\0';
    strlwr(sem);

    while (1) {
        Header("REGISTER COURSE");
        Note("(Press ESC to cancel process after completely register)");

        printf("\n\t\t\tYear: %d", yr);
        printf("\n\t\t\tSemester: %s", sem);
        Course curC;
        int cntCourse = 0;

        rewind(cfp);
        printf("\n\n\t\t\t      Course   |  Lecturer  |  St.Number  |  Maximum  |  Satisfy?  |   Status\n");

        while((fread(&curC, sizeof(curC), 1, cfp))) {
            if (curC.course_year == yr && !strcmp(strlwr(curC.course_semester), strlwr(sem))) {
                printf("\n\t\t\t(%d)", ++cntCourse);
                horizontal_outputCrs(curC);
            }
        }
        if (cntCourse == 0) {
            printf("\n\t\t\tThere is no course!\n\t\t\t"); fflush(stdin);
            if (getch() == 27) return;
        }
        //======end list==========

        Student curSt;
        int found=0;
        long long siz1 = sizeof(curSt);
        long long siz2 = sizeof(curC);

        rewind(fp);

        //char ID[max_len];
        //printf("\n\n\t\t\tEnter student's ID: "); fflush(stdin);
        //fgets(ID, max_len, stdin);
        //ID[strlen(ID)-1] = '\0';

        while((fread(&curSt, sizeof(curSt), 1, fp))) {
            if(curSt.ord != 0 && !strcmp(strupr(ID), strupr(curSt.student_id))) {
                found = 1; break;
            }
        }
        if (!found) {
            printf("\n\n\t\t\tSTUDENT CAN'T BE FOUND!"); fflush(stdin);
            getch();
            continue;

        }
        Line();
        printf("\n\t\t\t%s", curSt.student_id);
        printf(" | %s | number of course: %d", curSt.student_name, curSt.nStdCourse);
        printf("\n");

        if(found==1) {
            char reg_course[max_len];
            int stt = 0;
            while (1) {
                if (stt) break;
                printf("\n\n\t\t\tEnter to register / ESC to back..."); fflush(stdin);
                if (getch() == 27) return;

                int okila = 0;
                do {
                    okila = 1;
                    printf("\n\n\t\t\tCode of the course: "); fflush(stdin);
                    scanf("%s", &reg_course);
                    if (alreadyInClass(rfp, reg_course, ID) == 0) okila = 0;
                    if (okila == 0) {
                        printf("\n\t\t\tYou have already register this course!"); fflush(stdin);
                        if (getch() == 27) return;
                        continue;
                    }

                } while (okila == 0);

                //======= UPDATE COURSE ========
                {
                    int check = 0;
                    rewind(cfp);
                    while((fread(&curC, sizeof(curC), 1, cfp))) {
                        if (curC.course_year == yr && !strcmp(strlwr(curC.course_semester), strlwr(sem))) {
                            if (!strcmp(strupr(curC.classCod), strupr(reg_course))) {
                                //printf("\n\t\t\t%s %s", strlwr(curC.course_code), strlwr(reg_course));
                                check = 1; break;
                            }
                        }
                    }
                    if (!check) {
                        printf("\n\t\t\t NOT A COURSE! \n\t\t\t ENTER to try again / ESC to back..."); fflush(stdin);
                        char ck; ck = getch();
                        if (ck == 27) return;
                        stt = 0; continue;
                    }
                    else {
                        fseek(cfp, -siz2, SEEK_CUR);
                        if (curC.cur_amt >= curC.max_amt) {
                            printf("\n\t\t\t COURSE FULL SLOT! \n\t\t\t Please choose another course!");
                            printf("\n\n\t\t\tENTER to try again / ESC to back... "); fflush(stdin);
                            char ck; ck = getch();
                            if (ck == 27) return;
                            stt = 0; continue;
                        }
                        ++curC.cur_amt;
                        fwrite(&curC, sizeof(curC), 1, cfp); stt = 1;
                    }

                }

                //==============================
            }

            //===== UPDATE STUDENT REG =====
            {
            fseek(rfp, 0, SEEK_END);
            Reg newReg;

            strcpy(newReg.rID, strupr(ID));
            newReg.rYear = yr;
            strcpy(newReg.rSemester, sem);
            strcpy(newReg.crsCode, strupr(curC.classCod));

            strcpy(newReg.regCode, strupr(ID));
            strcpy(newReg.rName, curSt.student_name);
            int ordReg = cntReg(fp, rfp, ID) + 1;
            char last[max_len];
            itoa(ordReg, last, 10);
            strcat(newReg.regCode, last);
            newReg.regCode[strlen(newReg.regCode)] = '\0';
            strupr(newReg.regCode);
            printf("\n\t\t\tReg code: %s", newReg.regCode);

            fwrite(&newReg, sizeof(newReg), 1, rfp);}

            // printf("\n\n\t\t\tUPDATE SUCCESSFULLY!");
            fseek(fp, -siz1, SEEK_CUR);
            ++curSt.nStdCourse;
            fwrite(&curSt, sizeof(curSt), 1, fp);

            printf("\n\n\t\t\tRegistered successfully!"); fflush(stdin);
            //getch();
        }

        printf("\n\n\t\t\tENTER to continue / ESC to back..."); fflush(stdin);
        char ch;
        ch = getch();
        if (ch == 27) return;
        //end by press ESC
    }
}

void Cancel_registration(FILE * fp, FILE * cfp, FILE * rfp, char mp[][max_p]) {
    int ord;
    char Code[max_p], clCode[max_p]; // Code = regCode

    printf("\n\t\t\tClass order number: "); fflush(stdin);
    scanf("%d", &ord);

    rewind(fp);
    rewind(cfp);
    rewind(rfp);

    Student curSt;
    Course curC;
    Reg curR;

    strcpy(Code, mp[ord]);

    int found = 0;
    char ID[max_len];

    while((fread(&curR, sizeof(curR), 1, rfp))) {
        if (!strcmp(strupr(Code), strupr(curR.regCode))) {
            found = 1; break;
        }
    }
    if (found == 1) {
        fseek(rfp, -sizeof(curR), SEEK_CUR);
        printf("\n\t\t\t %s |   %d   |   %s\n", curR.crsCode, curR.rYear, curR.rSemester);
        curR.rYear = 0;

        strcpy(ID, curR.rID);
        strcpy(curR.regCode, "0");
        strcpy(clCode, curR.crsCode);

        fwrite(&curR, sizeof(curR), 1, rfp);
    }
    //====================================
    found = 0;
    while((fread(&curC, sizeof(curC), 1, cfp))) {
        if (!strcmp(strupr(clCode), strupr(curC.classCod))) {
            found = 1; break;
        }
    }
    if (found == 1) {
        fseek(cfp, -sizeof(curC), SEEK_CUR);
        curC.cur_amt -= 1;
        //curC.course_year = 0;
        fwrite(&curC, sizeof(curC), 1, cfp);
    }
    //=====================================
    found = 0;
    while((fread(&curSt, sizeof(curSt), 1, fp))) {
        if (!strcmp(strupr(ID), strupr(curSt.student_id))) {
            found = 1; break;
        }
    }

    if (found == 1) {
        fseek(fp, -sizeof(curSt), SEEK_CUR);
        --curSt.nStdCourse;
        fwrite(&curSt, sizeof(curSt), 1, fp);
    }

    printf("\n\t\t\tYour registration was canceled successfully!"); fflush(stdin);
    getch();
}

void View_course(FILE * cfp, FILE * rfp, char regsCode[], int year) {
    Header("VIEW YOUR COURSE");
    rewind(cfp);
    rewind(rfp);

    Course curC;
    Reg curR;
    int found = 0;

    char Code[max_p];
    Code[0] = '\0';

    while (fread(&curR, sizeof(curR), 1, rfp)) {
        if (curR.rYear != year) continue;
        if (!strcmp(strupr(curR.regCode),  strupr(regsCode))) {
            strcpy(Code, curR.crsCode);
        }
    }
    while ((fread(&curC, sizeof(curC), 1, cfp))) {
        if (curC.course_year != year) continue;
        if (!strcmp(strupr(Code), strupr(curC.classCod))) {
            found = 1;
            break;
        }
    }
    if (found) {
        printf("\n\n\t\t\tYear:             %d", year);
        printf("\n\t\t\tSemester:         %s", curC.course_semester);
        printf("\n\t\t\tCourse:           %s", curC.course_name);
        printf(" - %s", curC.classCod);
        printf("\n\t\t\tLecturer:         %s", curC.lecturer);

        printf("\n\n\t\t\tRequire number:   %d slots", curC.require_amt);
        printf("\n\t\t\tMax number:       %d slots", curC.max_amt);
        printf("\n\t\t\tCurrent number:   %d slot", curC.cur_amt); if (curC.cur_amt > 1) printf("s");
        fflush(stdin);
        getch();
    }

}

void Personal_view(FILE * fp, FILE * cfp, FILE * rfp, char ID[]) {
    while(1) {
        Header("INDIVIDUAL PROFILE");

        rewind(fp);
        if (fp == NULL) {
            printf("\n\t\t\tThere is no student!");
            getch();
            return;
        }

        int found = 0, cnt = 0;
        Student curSt;

        while((fread(&curSt, sizeof(curSt), 1, fp))) {
            ++cnt;
            //printf("\n\n\t\t\tord: %d", curSt.ord);
            //printf("\n\t\t\tID: %s", ID);
            //printf("\n\t\t\tcID: %s", curSt.student_id);
            if (curSt.ord != 0 && !strcmp(strupr(ID), strupr(curSt.student_id))) {
                printf("\n\t\t\tID:        %s", curSt.student_id);
                printf("\n\t\t\tFull name: %s", curSt.student_name);
                printf("\n\t\t\tMajor:     %s", strupr(curSt.student_major));
                printf("\n\t\t\tEmail:     %s", curSt.student_email);
                printf("\n\t\t\tPhone:     %s", curSt.student_phone);
                printf("\n\t\t\tAge:       %d", curSt.student_age);

                //printf("\n\n\t\t\tNumber of registered course: %d \n\n", curSt.nStdCourse);
                found = 1;
                break;
            }
        }

        if (!cnt) {
            printf("\n\t\t\tThere is no student!");
            printf("\n\t\t\tPress Enter to back..."); fflush(stdin);
            getch();
            return;

        }

        int yr; char sem[max_len];
        char mp[100][max_p]; // map order vs regCode

        if (found) {
            int stt = 0;
            do {
                Line();
                printf("\n\t\t\tEnter year to view your registrations!");
                printf("\n\n\t\t\tYear: "); fflush(stdin);
                scanf("%d", &yr);

                stt = 1;
                if (!isInt(yr)) stt = 0;
                if (yr < 2010 || yr > 2050) stt = 0;
                if (stt == 0) {
                    printf("\n\t\t\tInvalid year!\n\t\t\t");
                    if (getch() == 27) return;
                    else continue;
                }
            } while (stt == 0);

            Reg curReg;
            if (curSt.nStdCourse > 0) {
                rewind(rfp);
                int it = 0, found2 = 0;
                printf("\n\n\t\t\t     Course   |   Year   |  Semester  |    RegCode\n");
                while((fread(&curReg, sizeof(curReg), 1, rfp))) {
                    if (!strcmp(curReg.regCode, "0")) continue;
                    if (curReg.rYear == yr) {
                        if (!strcmp(strupr(ID), strupr(curReg.rID))) {
                            found2 = 1;
                            printf("\n\t\t\t(%d)", ++it);
                            printf(" %s |   %4d   |    %s    |   %s\n", curReg.crsCode, curReg.rYear, curReg.rSemester, curReg.regCode);

                            strcpy(mp[it], curReg.regCode); // MAP
                        }
                    }
                }
                //printf("\n\t\t\tFOUND: %d", found2);
                if (!found2) printf("\n\n\t\t\tStudent haven't registered any course yet!");
            }
            else printf("\n\n\t\t\tStudent haven't registered any course yet!");

        }
        else printf("\n\n\t\t\tStudent haven't registered any course yet!");
        Line();
        printf("\n\t\t\t1. Update personal info");
        printf("\n\t\t\t2. Cancel registration");
        printf("\n\t\t\t3. View your class");
        printf("\n\t\t\t0. Back");

        printf("\n\n\t\t\tEnter: "); fflush(stdin);

        char ch; scanf("%c", &ch);
        if (ch == '0') return;
        else if (ch == '1') Student_info(fp, ID);
        else if (ch == '2') {
            if (curSt.nStdCourse == 0) {
                printf("\n\t\t\tYou haven't registered any class yet!"); fflush(stdin);
                getch(); continue;
            }
            Cancel_registration(fp, cfp, rfp, mp);
        }
        else if (ch == '3') {
            if (curSt.nStdCourse == 0) {
                printf("\n\t\t\tYou haven't registered any class yet!"); fflush(stdin);
                getch(); continue;
            }
            char regsCode[max_p];
            int ord;

            printf("\n\t\t\tClass order number: "); fflush(stdin);
            scanf("%d", &ord);

            strcpy(regsCode, mp[ord]);
            //printf("\n\t\t\t   CODE: %s", regsCode); fflush(stdin); getch();
            View_course(cfp, rfp, regsCode, yr);
        }
        else {
            printf("\n\n\t\t\tInvalid input!"); fflush(stdin);
            getch();
            continue;
        }
    }
}

void Change_password(FILE * fp, char *ID) {
    Header("CHANGE PASSWORD");

    rewind(fp);
    if (fp == NULL) {
        printf("\n\t\t\tThere is no student!");
        getch();
        return;
    }

    int found = 0, cnt = 0;
    char pass[max_p];

    Student curSt;
    long long siz1 = sizeof(curSt);

    while((fread(&curSt, sizeof(curSt), 1, fp))) {
        if (!strcmp(strupr(ID), strupr(curSt.student_id))) {
            found = 1;
            break;
        }
    }
    int oke = 0;
    do {
        printf("\n\t\t\tOld password: "); fflush(stdin);

        char chr, checkPass[max_p];
        int it=0;

        do {
            fflush(stdin); chr=getch();
            if (chr == 13) {//ENTER
                checkPass[it] = '\0'; break;
            }
            else if (chr == 8) {//backspace
                if (it > 0) { printf("\b \b"); --it; }
                continue;
            }
            else if (chr == 9 || chr == 32) continue; // tab | space
            checkPass[it] = chr;
            ++it;
            printf("*");
        } while(chr != '\r');

        if (!strcmp(strlwr(checkPass), strlwr(curSt.password))) {
            oke = 1;
            break;
        }
        else {
            printf("\n\n\t\t\tIncorrect password!");
            printf("\n\t\t\tENTER to continue / ESC to back to previous...\n\t\t\t"); fflush(stdin);
            if (getch() == 27) return;
            oke = 0;
            continue;
        }

    } while (oke == 0);


    fseek(fp, -siz1, SEEK_CUR);

    char chr, newPass[max_p];
    int it=0;

    int stt = 0;
    do {
        stt = 1;
        //printf("\n\t\t\tNew password: "); fflush(stdin);
        newPass[0] = '\0'; it = 0;
        do {
            fflush(stdin); chr=getch();
            if (chr == 13) {//ENTER
                newPass[it] = '\0'; break;
            }
            else if (chr == 8) {//backspace
                if (it > 0) { printf("\b \b"); --it; }
                continue;
            }
            else if (chr == 9 || chr == 32) continue; // tab | space
            newPass[it] = chr;
            ++it;
            printf("*");
        } while(chr != '\r');
        printf("\n\t\t\tnew pass: %s", newPass);
        if (strlen(newPass) < 6) stt = 0;
        if (stt == 0) {
            printf("\n\t\t\tAt least 6 character!\n\t\t\t"); fflush(stdin);

            if (getch() == 27) return;
            continue;
        }
        if (stt == 1) {
            strcpy(curSt.password, newPass);
            break;
        }
    } while (stt == 0);


    fwrite(&curSt, sizeof(curSt), 1, fp);

    printf("\n\t\t\tPassword was changed successfully!"); fflush(stdin);
    getch();
}

void student_department(FILE * fp,FILE * cfp, FILE * rfp, char ID[], int* endp) {
    while(1) {
        Header("STUDENT MENU");

        printf("\n\n\n\t\t\t\t 1. Personal view");
        printf("\n\t\t\t\t 2. Registration");
        printf("\n\t\t\t\t 3. Change password");

        printf("\n\t\t\t\t 0. Exit\n\t");

        printf("\n\n\t\t\t\tEnter: "); fflush(stdin);
        char oop[1000];
        gets(oop);
        if (strlen(oop) > 1) {
            printf("\n\t\t\t\t\tINVALID INPUT!!! Try again...");
            fflush(stdin); getch();
            continue;
        }
        *endp = 0;
        switch(oop[0]) {
            case '1':
                Personal_view(fp, cfp, rfp, ID); break;
            case '2':
                Registration(fp, cfp, rfp, ID); break;
            case '3':
                Change_password(fp, ID); break;
            case '0':
                system("cls"); *endp = 1;
                return; break;
            default:
                printf("\n\t\t\t\t\tINVALID INPUT!!! Try again...");
                fflush(stdin); getch();
        }
    }
}

void login(FILE * fp, int *check, char *ID, int *endp) {
    Header("LOGIN TO STUDENT MENU");
    printf("\n\n");
    Line();
    Note("You need to enter password to log in");
    Note("(Valid password us not longer than 12 characters and does not contain special symbol.)");

    rewind(fp);
    if (fp == NULL) {
        printf("\n\t\t\tThere is no student!");
        getch();
        return;
    }

    printf("\n\n\t\t\tID: "); fflush(stdin);

    int found = 0;
    char sampPass[max_p];
    fgets(ID, max_len, stdin);
    ID[strlen(ID)-1] = '\0';

    Student curSt;

    while((fread(&curSt, sizeof(curSt), 1, fp))) {
        if (curSt.ord != 0) {
            if (!strcmp(strupr(ID), strupr(curSt.student_id))) {
                strcpy(sampPass, curSt.password);
                found = 1;
                break;
            }
        }
    }
    if (found == 0) {
        printf("\n\t\t\tStudent ID does not exist!\n\t\t\t"); //fflush(stdin);
        *check = 0;
        return;
    }

    printf("\t\t\tPassword: "); fflush(stdin);

    char ch, password[max_p];
    int it=0;

    do {
        fflush(stdin); ch=getch();
        if (ch == 13) {//ENTER
            password[it] = '\0'; break;
        }
        else if (ch == 8) {//backspace
            if (it > 0) { printf("\b \b"); --it; }
            continue;
        }
        else if (ch == 9 || ch == 32) continue; // tab | space
        password[it] = ch;
        ++it;
        printf("*");
    } while(ch != '\r');

    if (strcmp(password, sampPass)) {
        printf("\n\n\n");
        printMessageCenter("WRONG PASSWORD!");
        printf("\n\n\n");
        *check = 0;
        //printf("\n\t\t\tENTER to try again / ESC to back to previous...");
        return;
    }
    else {
        printf("\n\n\n");
        printMessageCenter("CORRECT PASSWORD");
        printf("\n\n\n");
        *check = 1;
        return;
    }
}

void LoginStudent(FILE * fp, FILE * cfp, FILE * rfp) {
    int endp;
    while (1) {
        int check = 0;
        char ID[max_len];
        endp = 0;

        login(fp, &check, &ID, &endp);

        if (!check) {
            printf("\n\t\t\tENTER to try again / ESC to back to previous...");
            fflush(stdin);
            if (getch() == 27) return;
            else continue;
        }
        printf("\n\t\t\tGETTING ACCESS TO STUDENT MENU...");
        //printf("\n\t\t\tID: %s", &ID);
        Load(3);

        student_department(fp, cfp, rfp, ID, &endp);
        if (endp == 1) return;
    }
}

void Help() {
    int opt = 0;
    do {
        Header("WELCOME TO STUDENT MANAGEMENT SYSTEM");
        printf("\n\t\t\t1. About");
        printf("\n\t\t\t2. How to use");
        printf("\n\t\t\t0. Back to main menu");
        printf("\n\n\t\t\tEnter: "); fflush(stdin);
        int opt; scanf("%d", &opt);
        switch(opt) {
            case 1:
                Header("MINI STUDENT MANAGEMENT SYSTEM");
                printf("\n\n\t\t\tThis is a mini project use to manage student information");

                printf("\n\t\t\tYou can use this mini program to store, add, update and remove students' registrations");
                printf("\n\n");
                printMessageCenter("_________________________________________");
                printf("\n\n\t\t\tAuthor: Loc Nguyen");
                printf("\n\t\t\tVersion: 1.1");
                printf("\n\t\t\tEmail: locndse160199@fpt.edu.vn");
                printf("\n\n");
                printMessageCenter("_________________________________________");
                printf("\n\n\t\t\tMany thanks to:");
                printf("\n\t\t\tMr. Hoang Tung - lecturer at FPTU and the mentor of this program.");
                fflush(stdin); getch();
                break;
            case 2:
                Header("USER GUIDE TO STUDENT MANAGEMENT SYSTEM");

                printf("\n\n\n\t\t\tThere are 3 semester in a year: Spring, Summer, Fall.)");
                printf("\n\n\t\t\tExample of a valid input:");
                printf("\n\t\t\t\tStudent name: Nguyen Van A");
                printf("\n\t\t\t\tMajor: SE ('software engineering')");
                printf("\n\n");
                printMessageCenter("_________________________________________");
                printf("\n\n");
                printMessageCenter("STAFF MENU");
                printf("\n\n\t\t\tChoose tab 1 to create new student record.");
                printf("\n\t\t\tChoose tab 2 to find a student and update information.");
                printf("\n\t\t\tChoose tab 3 to remove students (in case student drop out from university).");
                printf("\n\t\t\tChoose tab 4 to create new course and a class of course.");
                printf("\n\t\t\tChoose tab 5 to display course list and update information.");
                printf("\n\n");
                printMessageCenter("_________________________________________");
                printf("\n\n");
                printMessageCenter("STUDENT MENU");
                printf("\n\n\t\t\tIf you are a student and first time you login, your password is: 123456");
                printf("\n\t\t\tAfter login to the system, you have to change your password immediately");
                printf("\n\t\t\tbecause of security and privacy.");
                printf("\n\n\t\t\tThese are individual information of a student.");
                printf("\n\n\t\t\tChoose tab 1 to view more detail personal information.");
                printf("\n\t\t\tChoose tab 2 to register a class.");
                printf("\n\t\t\tChoose tab 3 to change password.");
                printf("\n\n");
                printMessageCenter("_________________________________________");
                printf("\n\n\t\t\tNotice that: ");
                printf("\n\t\t\t\tStudent ID is a string: ");
                printf("\n\t\t\t\tStudent name, Semester and Course are normal texts in Latina.");
                printf("\n\t\t\t\tStudent name, semester can not include special symbols (like , . - '");
                printf("\n\t\t\t\tPress Enter to complete an input section");
                fflush(stdin); getch();
                break;
            case 0:
                return;
            default:
                printf("\n\t\t\tINVALID INPUT!!! Try again...\n"); fflush(stdin); getch();
                break;
        }
    } while (!opt);
}

void menu() {
    FILE * fp;
    if((fp = fopen(FILE_NAME, "rb+")) == NULL) {
        if((fp = fopen(FILE_NAME, "wb+")) == NULL) {
            printf("Can't create or open Database.");
            return;
        }
    }

    FILE * cfp;
    if((cfp = fopen(COURSE_FILE, "rb+")) == NULL) {
        if((cfp = fopen(COURSE_FILE, "wb+")) == NULL) {
            printf("Can't create or open Database.");
            return;
        }
    }

    FILE * rfp;
    if((rfp = fopen(REG_FILE, "rb+")) == NULL) {
        if((rfp = fopen(REG_FILE, "wb+")) == NULL) {
            printf("Can't create or open Database.");
            return;
        }
    }

    while(1) {
        Header("WELCOME TO STUDENT MANAGEMENT SYSTEM");

        Line();
        printf("\n");
        printMessageCenter("WHO ARE YOU?");

        printf("\n\t\t\t\t 1. University staff");
        printf("\n\t\t\t\t 2. Student");

        printf("\n"); Line();
        printf("\n\t\t\t\t 3. Help");
        printf("\n\t\t\t\t 0. Exit\n\t");

        printf("\n\n\t\t\t\t Enter: "); fflush(stdin);
        char oop[1000];
        gets(oop);
        if (strlen(oop) > 1) {
            printf("\n\t\t\t\t\tINVALID INPUT!!! Try again...");
            fflush(stdin); getch();
            continue;
        }

        switch(oop[0]) {
            case '1':
                LoginStaff(fp, cfp, rfp);
                break;
            case '2':
                LoginStudent(fp, cfp, rfp);
                break;
            case '3':
                Help();
                break;
            case '0':
                system("cls"); printf("\n\n\n\t\t\t\tGOOD BYE!\n\n\n\n");
                return;
            default:
                printf("\n\n");
                printMessageCenter("INVALID INPUT!!! Try again...");
                fflush(stdin); getch();
        }
    }
}

int main() {
     SetConsoleTitle("STUDENT MANAGEMENT SYSTEM");
     menu();
     return 0;
}














