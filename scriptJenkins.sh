#!/bin/bash

echo TEST

RESULT=$(curl -s -H "Content-Type: text/xml" -d @dicomFileSetDescriptor_1.xml -X POST http://localhost:8090/validateDicomFileSetDescriptor)
REF='{"isValid":true,"message":""}'

if [ "$RESULT" == "$REF" ]
then
echo "validateDicomFileSetDescriptor : OK"
else
echo "validateDicomFileSetDescriptor : Error"
echo -e "Return :"$RESULT
echo -e "Attempted :"$REF
exit 1
fi




RESULT=$(curl -s -H "Content-Type: text/xml" -d @nonDicomFileSetDescriptor_1.xml -X POST http://localhost:8090/validateNonDicomFileSetDescriptor)
REF='{"isValid":true,"message":""}'

if [ "$RESULT" == "$REF" ]
then
echo "validateNonDicomFileSetDescriptor : OK"
else
echo "validateNonDicomFileSetDescriptor : Error"
echo -e "Return :"$RESULT
echo -e "Attempted :"$REF
exit 1
fi



RESULT=$(curl -s -H "Content-Type: text/xml" -d @dicomFileSetDescriptor_1.xml -X POST http://localhost:8090/importDicomFileSetDescriptor)
REF='{"res": "ImportDicomFileSetDescriptor Request received"}'

if [ "$RESULT" == "$REF" ]
then
echo "importDicomFileSetDescriptor : OK"
else
echo "importDicomFileSetDescriptor : Error"
echo -e "Return :"$RESULT
echo -e "Attempted :"$REF
exit 1
fi


RESULT=$(curl -s -H "Content-Type: text/xml" -d @nonDicomFileSetDescriptor_1.xml -X POST http://localhost:8090/importNonDicomFileSetDescriptor)
REF='{"res": "ImportNonDicomFileSetDescriptor Request received"}'

if [ "$RESULT" == "$REF" ]
then
echo "ImportNonDicomFileSetDescriptor : OK"
else
echo "ImportNonDicomFileSetDescriptor : Error"
echo -e "Return :"$RESULT
echo -e "Attempted :"$REF
exit 1
fi


RESULT=$(curl -s -H "Content-Type: text/xml" -X GET http://localhost:8090/getListQuerries)
NCHAR=${#RESULT}

if [ "$NCHAR" -gt "50" ]
then
echo "getListQuerries : OK"
else
echo "getListQuerries : Error"
echo -e "Return :"$RESULT
exit 1
fi


RESULT=$(curl    -s -H "Content-Type: text/xml" -X GET http://localhost:8090/testReturnReq?isReasoning=false)
NCHAR=${#RESULT}

if [ "$NCHAR" -gt "50" ]
then
echo "testReturnReq : OK"
else
echo "testReturnReq : Error"
echo -e "Return :"$RESULT
exit 1
fi



git checkout stable

heure=$(date +%H%M)
jour=$(date +%Y%m%d)


git add .

git commit -m Stable"_"$jour"_"$heure

git push

git checkout master



exit 0


