INPUT1="12345"
INPUT2="12345678901234"
INPUT3=""
INPUT4="123"
EXPECTATION="History buffer: 1, 0, 14, 5  "

make clean
make

echo "$INPUT1" > /dev/var1
echo "$INPUT2" > /dev/var1
echo "$INPUT3" > /dev/var1
echo "$INPUT4" > /dev/var1

ACTUAL=$(cat /proc/var1)
if [[ "$EXPECTATION" = "$ACTUAL" ]]; then 
echo "Test is OK"
else
echo "Test is failed" >&2
fi
