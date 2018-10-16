#!/bin/bash

echo TEST

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/dicomFileSetDescriptor_1.xml -X POST http://localhost:8090/validateDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/nonDicomFileSetDescriptor_1.xml -X POST http://localhost:8090/validateNonDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/dicomFileSetDescriptor_1.xml -X POST http://localhost:8090/importDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/nonDicomFileSetDescriptor_1.xml -X POST http://localhost:8090/importNonDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/dicomFileSetDescriptor_2.xml -X POST http://localhost:8090/importDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/nonDicomFileSetDescriptor_2.xml -X POST http://localhost:8090/importNonDicomFileSetDescriptor)
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


RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/dicomFileSetDescriptor_3.xml -X POST http://localhost:8090/importDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/nonDicomFileSetDescriptor_3.xml -X POST http://localhost:8090/importNonDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/dicomFileSetDescriptor_4.xml -X POST http://localhost:8090/importDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/nonDicomFileSetDescriptor_4.xml -X POST http://localhost:8090/importNonDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/dicomFileSetDescriptor_5.xml -X POST http://localhost:8090/importDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -d @uploadFiles/nonDicomFileSetDescriptor_5.xml -X POST http://localhost:8090/importNonDicomFileSetDescriptor)
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

RESULT=$(curl -s -H "Content-Type: text/xml" -X GET http://localhost:8090/getResearchStudies)
NCHAR=${#RESULT}

if [ "$NCHAR" -gt "50" ]
then
echo "getListQuerries : OK"
else
echo "getListQuerries : Error"
echo -e "Return :"$RESULT
exit 1
fi

RESULT=$(curl -s -H "Content-Type: text/xml" -X GET http://localhost:8090/testReturnReq?isReasoning=false)
NCHAR=${#RESULT}

if [ "$NCHAR" -gt "50" ]
then
echo "testReturnReq : OK"
else
echo "testReturnReq : Error"
echo -e "Return :"$RESULT
exit 1
fi

RESULT=$(curl -s -H "Content-Type: text/xml" -X GET http://localhost:8090/testReturnReq?isReasoning=false)
NCHAR=${#RESULT}

if [ "$NCHAR" -gt "50" ]
then
echo "testReturnReq : OK"
else
echo "testReturnReq : Error"
echo -e "Return :"$RESULT
exit 1
fi

exit 0


RESULT=$(curl -s -X GET "http://localhost:8090/requestFromList?id=Request" -H "accept: text/xml")


