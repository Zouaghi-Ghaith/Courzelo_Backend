from math import e
# load default skills data base
from skillNer.general_params import SKILL_DB
# import skill extractor
from skillNer.skill_extractor_class import SkillExtractor

import numpy as np
import pandas as pd
import nltk
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords
from string import punctuation
from nltk.stem import WordNetLemmatizer

from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

from scipy.sparse import csr_matrix, hstack
import mysql.connector
import sys

def main():
    searchTerm = sys.argv[1]
    searchTerm = int(searchTerm)

    conn = mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="Corzello"
    )
    # Create a cursor object to execute SQL queries
    cursor = conn.cursor()
    # Define your SQL query
    sql_query = """
    SELECT
        cm.nom_class AS class_name,
        cm.filiere AS class_filiere,
        c.nom_cour AS course_name,
        c.details_cour AS course_details,
        pe.nom_prog AS program_name,
        pe.description_prog AS program_description,
        m.nom_module AS module_name,
        m.description AS module_description
    FROM
        etudiant e
    JOIN
        class_management cm ON e.idclass = cm.idclass
    JOIN
        prof p ON cm.idclass = p.idclass
    JOIN
        module_entity m ON p.idprof = m.prof_idprof
    JOIN
        cours c ON m.id_module = c.module_id
    JOIN
        prog_educatif pe ON c.prog_educatif_id_prog_educatif = pe.id_prog_educatif
    WHERE
        e.idetudiant = %s;

    """

    # Execute the SQL query
    cursor.execute(sql_query, (searchTerm,))

    # Fetch all the rows from the result
    result = cursor.fetchall()
    # Close the cursor and connection
    cursor.close()

    # Print the result
    for row in result:
        print(row)

    # Create a cursor object to execute SQL queries
    cursor = conn.cursor()

    # Define the SQL query
    sql_query = """
        SELECT
            job_title,
            job_description,
            profile_description,
            entreprise_decription
        FROM
            recruitement_process_details
        WHERE
            id_user = %s;
    """

    # Execute the SQL query
    cursor.execute(sql_query, (searchTerm,))

    # Fetch the results
    results = cursor.fetchall()

    # Print the results
    for row in results:
        print(row)

    # Close the cursor and connection
    cursor.close()


    nltk.download('punkt')
    nltk.download('stopwords')
    nltk.download('wordnet')
    nltk.download('omw-1.4')

    result[0]=str(result[0])
    results[0]=str(results[0])
    tokenized1=[]
    text_to_tokenize = str(result[0])
    word_sent = word_tokenize(text_to_tokenize.lower().replace("   ", " "))
    _stopwords = set(stopwords.words('french') + list(punctuation)+list("·")+list('–')+list('--')+list('●')+list('•')+list('’'))
    word_sent=[word for word in word_sent if word not in _stopwords]
    lemmatizer = WordNetLemmatizer()
    word_sent= [lemmatizer.lemmatize(word) for word in word_sent]
    tokenized1.append(word_sent)
    tokenized2=[]
    text_to_tokenize = str(results[0])
    word_sent = word_tokenize(text_to_tokenize.lower().replace("   ", " "))
    _stopwords = set(stopwords.words('french') + list(punctuation)+list("·")+list('–')+list('--')+list('●')+list('•')+list('’'))
    word_sent=[word for word in word_sent if word not in _stopwords]
    lemmatizer = WordNetLemmatizer()
    word_sent= [lemmatizer.lemmatize(word) for word in word_sent]
    tokenized2.append(word_sent)

    # Join the tokenized words back into sentences
    text1 = [' '.join(tokens) for tokens in tokenized1]
    text2 = [' '.join(tokens) for tokens in tokenized2]

    # Initialize TF-IDF vectorizer
    tfidf_vectorizer = TfidfVectorizer()

    # Fit and transform the text data
    tfidf_matrix = tfidf_vectorizer.fit_transform(text1 + text2)
    from sklearn.metrics.pairwise import cosine_similarity

    # Calculate cosine similarity between the TF-IDF vectors
    cosine_similarities = cosine_similarity(tfidf_matrix[0:1], tfidf_matrix[1:2])

    # Print the cosine similarity value
    print("Cosine Similarity:", cosine_similarities[0][0])
    cosine_similarities = cosine_similarities*100
    print(cosine_similarities[0])
    # Assuming cosine_similarities[0][0] is a float value
    cosine_similarity = cosine_similarities[0][0]
    formatted_similarity = "{:.2f}".format(cosine_similarity)

    print(formatted_similarity)
    formatted_similarity=float(formatted_similarity)
    cursor = conn.cursor()
    update_query =  """UPDATE recruitement_process_details SET percentage = %s WHERE id_user = %s"""

    # Parameters for the update query as a tuple
    parameters = (formatted_similarity,searchTerm)  # Note the comma to create a tuple with one element

    # Print the update query for debugging
    print("Update Query:", update_query)

    # Execute the update query
    cursor.execute(update_query, parameters)

    # Commit changes to the database
    conn.commit()

    # Close cursor and connection
    cursor.close()
    conn.close()



# Run the main function when the script is called
if __name__ == "__main__":
    main()
