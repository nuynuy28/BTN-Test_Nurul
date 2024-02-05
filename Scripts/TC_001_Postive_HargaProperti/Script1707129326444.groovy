import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.setText(findTestObject('Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/Penghasilan_total'), '30000000')

WebUI.takeScreenshot()

WebUI.setText(findTestObject('Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/pengeluaran'), '27000000')

WebUI.takeScreenshot()

WebUI.selectOptionByValue(findTestObject('Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/jangka_waktu'), '4', true) 
   

WebUI.click(findTestObject('Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/hitung'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/h5_Harga Properti Maksimal Kamu'), 30)


/* Verifikasi menggunakan Formula yang digunakan saat ini yaitu :
 * (Penghasilan - Pengeluaran) * Jumlah Bulan / 3*/
def bil_penghasilan = Double.parseDouble(Penghasilan_total)
def bil_pengeluaran = Double.parseDouble(pengeluaran)

String select_jw = jangka_waktu.replaceAll('[^0-9]', '')

def input_jw = Integer.parseInt(select_jw)

def hitung_sisa_penghasilan = input_Penghasilan - input_Pengeluaran

def hitung_Jangka_Waktu = 12 * input_jw

def hitung_harga_properti = (hitung_sisa_penghasilan * hitung_Jangka_Waktu) / 3

println(hitung_harga_properti)

String hasil_hargaProperti = String.format('%.0f', hitung_harga_properti)

println(hasil_hargaProperti)

def result_propertiHarga = WebUI.getText(findTestObject('Object Repository/Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/result'))

String get_hasil_RekomHarga = result_propertiHarga.replaceAll('[^0-9]', '')

WebUI.verifyEqual(get_hasil_RekomHarga, hasil_hargaProperti)

WebUI.takeScreenshot()

WebUI.closeBrowser()

