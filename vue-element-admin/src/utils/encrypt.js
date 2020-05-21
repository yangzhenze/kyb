import CryptoJS from 'crypto-js'

const encrypt = {
  encryptByDE(message, key) {
    const keyHex = CryptoJS.enc.Utf8.parse(key)
    const encrypted = CryptoJS.DES.encrypt(message, keyHex, {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
    })
    return encrypted.toString()
  }
}

export default encrypt

