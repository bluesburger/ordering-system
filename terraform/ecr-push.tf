# Definição de um recurso de execução local para fazer o push da imagem
resource "null_resource" "push_image_to_ecr" {
  provisioner "local-exec" {
    command = "aws ecr get-login-password --region ${var.defaultRegion} | docker login --username AWS --password-stdin ${var.accountId}.dkr.ecr.${var.defaultRegion}.amazonaws.com && docker build -t ${var.accountId}.dkr.ecr.${var.defaultRegion}.amazonaws.com/ordering-system:latest ../ && docker push ${var.accountId}.dkr.ecr.${var.defaultRegion}.amazonaws.com/ordering-system:latest"
  }
}
