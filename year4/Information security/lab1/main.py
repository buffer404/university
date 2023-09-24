alphabet = list("абвгдежзийклмнопрстуфхцчшщъыьэюя")
n = 25
secret_word = "шифровка"
secret_alphabet = list("шифровкабгдежзйлмнпстухцчщъыьэюя")

# alphabet = list("abcdefghijklmnopqrstuvwxyz")
# n = 5
# secret_word = "diplomat"
# secret_alphabet = list("vwxyzdiplomatbcefghjknqrsu")

# шифрование данных
def encrypt(data):
    result = []
    for char in data:
        if char in alphabet:
            result.append(secret_alphabet[alphabet.index(char)])
        elif chr(ord(char) + 32) in alphabet:
            result.append(chr(ord(secret_alphabet[alphabet.index(chr(ord(char) + 32))]) - 32))
        else:
            result.append(char)
    return result

#дешифрование данных
def decrypt(data):
    result = []
    for char in data:
        if char in alphabet:
            result.append(alphabet[secret_alphabet.index(char)])
        elif chr(ord(char) + 32) in alphabet:
            result.append(chr(ord(alphabet[secret_alphabet.index(chr(ord(char) + 32))]) - 32))
        else:
            result.append(char)
    return result

#запись в файл закодированное сообщение
def write_encrypt(file, result):
    fr.write("Полученная закодированная строка:\n")
    fr.write(''.join(result) + '\n\n')

#запись в файл декодированное сообщение
def write_decrypt(file, result):
    fr.write("Полученная расшифрованная строка:\n")
    fr.write(''.join(result) + '\n')


if __name__ == '__main__':
    fd = open('data.txt', 'r')
    data = list(fd.read())
    fd.close()

    fr = open('result.txt', 'r+')

    encrypt_result = encrypt(data)
    write_encrypt(fr, encrypt_result)

    decrypt_result = decrypt(encrypt_result)
    write_decrypt(fr, decrypt_result)

    fr.close()
