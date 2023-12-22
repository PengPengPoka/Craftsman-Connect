import numpy as np
from flask import Flask, request, jsonify, render_template
import pickle
import string

# create flask app
app = Flask(__name__)

# load pickle file
model = pickle.load(open("recommendation.pkl","rb"))

@app.route("/")
def Home():
    return render_template("index.html")

@app.route("/predict", methods = ["POST"])
def predict():
    float_features = [str(x) for x in request.form.values()]
    features = [np.array(float_features)]
    prediction = model.predict(features)

    return render_template("index.html", prediction_text = "Tukang yang dibutuhkan adalah {}".format(prediction))

if __name__ == "__main__":
    app.run(debug=True)