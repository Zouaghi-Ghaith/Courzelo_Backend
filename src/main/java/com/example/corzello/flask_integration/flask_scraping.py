from selenium import webdriver
from selenium.webdriver.common.by import By
import time
import mysql.connector
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException
import sys

def main():
    searchTerm = sys.argv[1]
    driver = webdriver.Chrome()

    #driver = webdriver.Chrome(ChromeDriverManager().install())

    driver.get("https://www.welcometothejungle.com/fr/jobs")

    time.sleep(5)
    #driver.find_element(By.XPATH, "//button[@class='search-global-typeahead__collapsed-search-button']").click()

    recherche = driver.find_element(By.XPATH, "//input[@id='search-query-field']")
    recherche.clear()
    recherche.send_keys(searchTerm)
    print("done 1")


    #recherche.send_keys(Keys.ENTER)

    #time.sleep(5)
    array_titre = []
    array_job_description = []
    array_profile_description =[]
    array_entreprise_decription =[]
    driver.find_element(By.XPATH,"//button[@id='axeptio_btn_acceptAll']").click()
    time.sleep(5)
    driver.find_element(By.XPATH, "//button[@id='jobs-search-filter-contract']").click()
    print("done 2")
    time.sleep(5)
    driver.find_element(By.XPATH, "//input[@value='internship']").click()
    time.sleep(5)
    def get_titre():
        title = driver.find_element(By.XPATH, "//titre")
        title_text = title.get_attribute("textContent")
        array_titre.append(title_text)
        print(title_text)
    print("done 3")
    def see_all():
        voir_plus = driver.find_elements(By.XPATH, "//i[@class='sc-fUBkdm jFJIOY  wui-icon-font']")
        print(len(voir_plus))
        i = 0
        while i < len(voir_plus):
            try:
                # Scroll the element into view
                driver.execute_script("arguments[0].scrollIntoView();", voir_plus[i])
                time.sleep(2)  # Give some time for scrolling

                driver.execute_script("arguments[0].click();", voir_plus[i])
                time.sleep(5)
            except Exception as e:
                print("Exception:", e)

            i += 1
    print("done 4")
    def get_job_description():
        try:
            job_description = driver.find_element(By.XPATH, "//div[@data-testid='job-section-description']")
            job_description_text = job_description.get_attribute("textContent")
            print(job_description_text)
            array_job_description.append(job_description_text)
        except NoSuchElementException:
            print("Job description element not found.")
            array_job_description.append("")
    print("done 5")
    def get_profile_description():
        try:
            profile_description = driver.find_element(By.XPATH, "//div[@data-testid='job-section-experience']")
            profile_description_text = profile_description.get_attribute("textContent")
            print(profile_description_text)
            array_profile_description.append(profile_description_text)
        except NoSuchElementException:
            print("Profile description element not found.")
            array_profile_description.append("")
    print("done 6")
    def get_entreprise_decription():
        try:
            entreprise_description = driver.find_element(By.XPATH, "//div[@class='sc-bXCLTC eCbjRu sc-1h2tno4-0 dQPmSZ']")
            entreprise_description_text = entreprise_description.get_attribute("textContent")
            print(entreprise_description_text)
            array_entreprise_decription.append(entreprise_description_text)
        except NoSuchElementException:
            print("Entreprise description element not found.")
            array_entreprise_decription.append("")
    print("done 7")
    # Scroll to the bottom of the page
    while True:
        # Get the current height of the page
        last_height = driver.execute_script("return document.body.scrollHeight")

        # Scroll to the bottom
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")

        # Wait for some time to let the content load
        time.sleep(2)

        # Calculate new height after scrolling
        new_height = driver.execute_script("return document.body.scrollHeight")

        # If the height has not changed after scrolling, break the loop
        if new_height == last_height:
            break

    print("done 77")
    # Wait for the elements to load after scrolling
    time.sleep(5)
    select = driver.find_elements(By.XPATH, "//div[@class='sc-bXCLTC sc-epqpcT eVFDKT sc-6i2fyx-4 ctzXZg']")
    print(select)
    i = 0
    while i < len(select):
        print("start while")
        for s in select:
            sselect = driver.find_elements(By.XPATH, "//div[@class='sc-bXCLTC sc-epqpcT eVFDKT sc-6i2fyx-4 ctzXZg']")
            print("len sselect:", len(sselect))

            # Check if sselect is not empty and if it has enough elements
            if sselect and i < len(sselect):
                try:
                    # Scroll to the element
                    driver.execute_script("arguments[0].scrollIntoView();", sselect[i])
                    time.sleep(2)  # Give some time for scrolling

                    # Click the element
                    sselect[i].click()
                    time.sleep(5)
                    #print('post number', i+1)
                    see_all()
                    time.sleep(2)
                    #print("titre")
                    #get_titre()
                    time.sleep(2)
                    print("job")
                    get_job_description()
                    time.sleep(2)
                    print("profile")
                    get_profile_description()
                    time.sleep(2)
                    print("entreprise")
                    get_entreprise_decription()
                    driver.back()
                    time.sleep(5)
                except Exception as e:
                    print("Exception:", e)

        i += 1
    data_length = len(array_job_description)

    # Se connecter à la base de données MySQL
    conn = mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="Corzello"
    )
    cursor = conn.cursor()
    for i in range(data_length):
        #titre = array_titre[i]
        job_description = array_job_description[i]
        profile_description = array_profile_description[i]
        entreprise_description = array_entreprise_decription[i]

        # Insert data into the table (assuming table name is 'your_table_name')
        cursor.execute("INSERT INTO proposed_job_offer (job_description, profile_description, entreprise_decription) VALUES (%s, %s, %s)",
                       (job_description, profile_description, entreprise_description))


    # Commit changes and close connection
    conn.commit()
    conn.close()


# Run the main function when the script is called
if __name__ == "__main__":
    main()
