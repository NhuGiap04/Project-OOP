import argparse
from transformers import AutoTokenizer, AutoModel
import torch
import torch.nn.functional as F


# Mean Pooling - Take attention mask into account for correct averaging
def mean_pooling(model_output, attention_mask):
    token_embeddings = model_output[0]  # First element of model_output contains all token embeddings
    input_mask_expanded = attention_mask.unsqueeze(-1).expand(token_embeddings.size()).float()
    return torch.sum(token_embeddings * input_mask_expanded, 1) / torch.clamp(input_mask_expanded.sum(1), min=1e-9)


def generate_embedding(text: str):
    tokenizer = AutoTokenizer.from_pretrained("all-MiniLM-L6-v2")
    model = AutoModel.from_pretrained("all-MiniLM-L6-v2")

    sentences = [text]

    # Tokenize sentences
    encoded_input = tokenizer(sentences, padding=True, truncation=True, return_tensors='pt')

    # Compute token embeddings
    with torch.no_grad():
        model_output = model(**encoded_input)

    # Perform pooling
    sentence_embeddings = mean_pooling(model_output, encoded_input['attention_mask'])

    # Normalize embeddings
    sentence_embeddings = F.normalize(sentence_embeddings, p=2, dim=1)

    return sentence_embeddings.tolist()


def main():
    parser = argparse.ArgumentParser(description='Generate embedding for a given text.')
    parser.add_argument('text', type=str, help='The text to generate embedding for')

    args = parser.parse_args()

    embedding = generate_embedding(args.text)
    print(embedding[0])


if __name__ == '__main__':
    main()
